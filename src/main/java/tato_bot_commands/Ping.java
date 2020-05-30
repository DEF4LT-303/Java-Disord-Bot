package tato_bot_commands;

import tato_bot.main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Ping extends ListenerAdapter {
	
	public void onGuildMessageReceived (GuildMessageReceivedEvent event)
	{
		String[] args = event.getMessage().getContentRaw().split("\\s+");
		
		String gateway = Long.toString(event.getJDA().getGatewayPing());
		
		
		if (args[0].equalsIgnoreCase(main.prefix + "ping"))
		{
			//event.getChannel().sendMessage("🥔 Pong!").queue();
			
			
			EmbedBuilder ping = new EmbedBuilder();
			
			ping.setDescription("🥔 Pong!");
			ping.setColor(0x00aa00);
			ping.addField("Gateway Ping: ", gateway, false);
			event.getChannel().sendMessage(ping.build()).queue();
		}
	}

}