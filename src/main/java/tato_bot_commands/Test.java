package tato_bot_commands;

import tato_bot.main;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Test extends ListenerAdapter {
	
	public void onGuildMessageReceived (GuildMessageReceivedEvent event)
	{
		String[] args = event.getMessage().getContentRaw().split("\\s+");
		
		String test = event.getJDA().getInviteUrl();
		
		if (args[0].equalsIgnoreCase(main.prefix + "test"))
		{
			event.getChannel().sendMessage("invite url: " + test).queue();
		}
	}
	

}
