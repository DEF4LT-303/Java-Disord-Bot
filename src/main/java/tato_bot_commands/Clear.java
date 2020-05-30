package tato_bot_commands;

import java.util.List;
import java.util.concurrent.TimeUnit;

import tato_bot.main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Clear extends ListenerAdapter{
	
	public void onGuildMessageReceived(GuildMessageReceivedEvent event)
	{
		String[] args = event.getMessage().getContentRaw().split("\\s+"); //splits input with space
		
		if (args[0].equalsIgnoreCase(main.prefix + "clear"))
		{
			if (args.length < 2)
			{
				//fail and usage
				EmbedBuilder usage = new EmbedBuilder();
				usage.setColor(0xff3923);
				usage.setTitle("⚠ Specify an amount!");
				usage.setDescription("Usage: `" + main.prefix + "clear [amount]`");
				event.getChannel().sendMessage(usage.build()).queue();
			}
			else
			{
				try
				{
					//success
					List<Message> messages = event.getChannel().getHistory().retrievePast(Integer.parseInt(args[1])).complete();
					event.getChannel().deleteMessages(messages).queue();
					
					EmbedBuilder success = new EmbedBuilder();
					success.setColor(0xff3923);
					success.setTitle("✅ Deleted " + args[1] + " messages");
					event.getChannel().sendMessage(success.build()).queue();
					
					
				}
				catch (IllegalArgumentException e)
				{
					//too many messages
					if (e.toString().startsWith("java.lang.IllegalArgumentException: Message retrival"))
					{
						EmbedBuilder error = new EmbedBuilder();
						error.setColor(0xff3923);
						error.setTitle("⚠ Too many messages selected!");
						error.setDescription("Select between 1 to 100");
						event.getChannel().sendMessage(error.build()).queue();
					}
					
					else
					{
						//older messages
						EmbedBuilder error = new EmbedBuilder();
						error.setColor(0xff3923);
						error.setTitle("⚠  Messages older than 2 weeks cannot be deleted!");
						error.setDescription("Select between 1 to 100");
						event.getChannel().sendMessage(error.build()).queue();
					}
					
					
				}
				
			}
		}
	}

}