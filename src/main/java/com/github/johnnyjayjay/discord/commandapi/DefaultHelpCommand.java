package com.github.johnnyjayjay.discord.commandapi;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public final class DefaultHelpCommand extends AbstractHelpCommand {
    
    public DefaultHelpCommand(CommandSettings settings) {
        super(settings);
    }

    @Override
    public void provideGeneralHelp(CommandEvent event, String prefix, Map<String, ICommand> commands) {
        EmbedBuilder embed = getBuilder(event.getGuild().getSelfMember());
        String helpLabels = "[" + String.join("|", settings.getLabels(this)) + "]";
        embed.appendDescription("To learn more about a specific command, just call `").appendDescription(prefix)
                .appendDescription(helpLabels).appendDescription(" <label>`.\nThe following commands are currently available:\n");
        String commandsList = commands.keySet().stream().map((label) -> prefix + label).collect(Collectors.joining(", "));
        if (commandsList.length() < 1010)
            embed.addField("Commands", "```\n" + commands + "```", false);
        else
            embed.addField("Warning", "Too many commands to show.", false);
        event.getChannel().sendMessage(embed.build()).queue();
    }

    @Override
    public void provideSpecificHelp(CommandEvent event, String prefix, ICommand command, Set<String> labels) {
        EmbedBuilder embed = getBuilder(event.getGuild().getSelfMember());
        embed.appendDescription("**Command Info for:** `").appendDescription("[")
                .appendDescription(String.join("|", settings.getLabels(command))).appendDescription("]`\n\n")
                .appendDescription(settings.getCommands().get(event.getArgs()[0]).info(event.getMember()));
        event.getChannel().sendMessage(embed.build()).queue();
    }

    private EmbedBuilder getBuilder(Member selfMember) {
        return new EmbedBuilder().setColor(settings.getHelpColor() != null ? settings.getHelpColor() : selfMember.getColor());
    }

    @Override
    public String info(Member member) {
        return "Shows all available commands or provides help for a specific command. Usage: `"
                + this.settings.getPrefix(member.getGuild().getIdLong()) + "help <label>`";
    }
}
