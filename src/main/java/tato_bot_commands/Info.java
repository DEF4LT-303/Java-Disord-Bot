package tato_bot_commands;

import tato_bot.main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Info extends ListenerAdapter {
	
	public void onGuildMessageReceived(GuildMessageReceivedEvent event)
	{
		String[] args = event.getMessage().getContentRaw().split("\\s+"); //splits with space
		
		if (args[0].equalsIgnoreCase(main.prefix + "help"))
		{
			EmbedBuilder info = new EmbedBuilder();
			info.setTitle("🥔 Information 🥔");
			info.setDescription("A bot under development...");
			info.addField("Version", "1.1", false);
			info.addField("Commands", "`-help`: Displays this message\n"
					+ "`-ping`: Shows gateway ping\n"
					+ "`-panda`: Shows a random picture of Panda\n"
					+ "`-ud [term]`: Urban Dictionary\n", false);
			info.addField("Actions", "`-hug [user]`: To hug user\n", false);
			info.setColor(0xfcdf03);
			info.setFooter("Created by Tato#3453", event.getAuthor().getAvatarUrl());
			
		//	event.getChannel().sendTyping().queue();
			event.getChannel().sendMessage(info.build()).queue();
			info.clear();
		}
	}

}