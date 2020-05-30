package tato_bot_commands;

import java.util.Random;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONException;
import kong.unirest.json.JSONObject;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import tato_bot.main;

public class Actions extends ListenerAdapter {
	
	
	public void onGuildMessageReceived (GuildMessageReceivedEvent event)
	{
		String[] args = event.getMessage().getContentRaw().split("\\s+");
		
		 
		 Random index = new Random();
		 int i = index.nextInt(15);
		
		if (args[0].equalsIgnoreCase(main.prefix + "hug"))
		{
			try {
				
				final HttpResponse<JsonNode> response = Unirest.get("https://api.giphy.com/v1/gifs/search?api_key=DcpL3NFY1IXk1MQyROya3EI36YByjRul&q=anime hug&limit=15&offset=0&rating=G&lang=en").asJson(); //sets API
				
				JSONArray array = response.getBody().getObject().getJSONArray("data");
				JSONObject obj = array.getJSONObject(i);
				String url = obj.getString("id");
				
				String hug = "https://media.giphy.com/media/"+url+"/source.gif";
				
				EmbedBuilder gif = new EmbedBuilder();
				
				gif.setImage(hug);
				gif.setDescription(event.getAuthor().getAsMention()+" hugs "+args[1]+" !");
				gif.setColor(0xfcb603);
				event.getChannel().sendMessage(gif.build()).queue();
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				event.getChannel().sendMessage(e.toString());
			}
			
		}
		
	}

}
