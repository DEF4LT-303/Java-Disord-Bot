package tato_bot_commands;



import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONException;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import tato_bot.main;

public class Image extends ListenerAdapter {
	
	
	
	public void onGuildMessageReceived (GuildMessageReceivedEvent event)
	{
		String[] args = event.getMessage().getContentRaw().split("\\s+");
		
		
		
		if (args[0].equalsIgnoreCase(main.prefix + "panda"))
		{
			try {
				final HttpResponse<JsonNode> response = Unirest.get("https://some-random-api.ml/img/panda").asJson(); //sets API
				//event.getChannel().sendMessage(response.getBody().getObject().getString("link")).queue(); //gets response
				
				String panda = response.getBody().getObject().getString("link");
				
				
				EmbedBuilder image = new EmbedBuilder();
				
				image.setImage(panda);
				image.setDescription("🐼 Here's a Panda for you...");
				image.setColor(0xfcb603);
				event.getChannel().sendMessage(image.build()).queue();
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				event.getChannel().sendMessage(e.toString());
			}
			
			
		}
	}
}