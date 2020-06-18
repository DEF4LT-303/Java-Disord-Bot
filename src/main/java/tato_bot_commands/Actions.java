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
		
		 
		 int index[] = {1, 2, 4, 5, 6, 7, 8, 9, 10};
		 int i = new Random().nextInt(index.length);
		
		if (args[0].equalsIgnoreCase(main.prefix + "hug"))
		{
			try {
				
				final HttpResponse<JsonNode> response = Unirest.get("https://api.giphy.com/v1/gifs/search?api_key=DcpL3NFY1IXk1MQyROya3EI36YByjRul&q=anime+hug&limit=10&offset=0&rating=G&lang=en").asJson(); //sets API
				
				JSONArray array = response.getBody().getObject().getJSONArray("data");
				JSONObject images = array.getJSONObject(i).getJSONObject("images").getJSONObject("original");
				
				String url = images.getString("url");
			//	event.getChannel().sendMessage(url).queue();
				
				EmbedBuilder gif = new EmbedBuilder();
				
				gif.setImage(url);
				gif.setDescription(event.getAuthor().getAsMention()+" hugs "+args[1]+" !");
				gif.setColor(0xfcb603);
				gif.setFooter("Powered by Giphy", "https://media.giphy.com/avatars/editor/TE3GhSYzaRns.jpg");
				event.getChannel().sendMessage(gif.build()).queue();
				
			} catch (JSONException e) {
				e.printStackTrace();
				event.getChannel().sendMessage(e.toString());
			}}
			
			else if (args[0].equalsIgnoreCase(main.prefix + "pat"))
			{
				try {
					
					final HttpResponse<JsonNode> response = Unirest.get("https://api.tenor.com/v1/search?q=anime+pat&key=2DHKTY5FZE2N&limit=2").asJson(); //sets API
					
					JSONArray array = response.getBody().getObject().getJSONArray("results");
					JSONObject source = array.getJSONObject(0);
					
					String url = source.getString("url");
					event.getChannel().sendMessage(url).queue();
					
					EmbedBuilder gif = new EmbedBuilder();
					
					gif.setImage(url);
					gif.setDescription(event.getAuthor().getAsMention()+" hugs "+args[1]+" !");
					gif.setColor(0xfcb603);
					gif.setFooter("Powered by Giphy", "https://media.giphy.com/avatars/editor/TE3GhSYzaRns.jpg");
					event.getChannel().sendMessage(gif.build()).queue();
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					event.getChannel().sendMessage(e.toString());
				}
			}
			
		
		
	}

}
