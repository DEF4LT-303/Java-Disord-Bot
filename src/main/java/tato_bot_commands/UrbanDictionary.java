package tato_bot_commands;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import tato_bot.main;

public class UrbanDictionary extends ListenerAdapter {
	
	public void onGuildMessageReceived (GuildMessageReceivedEvent event)
	{
		String[] args = event.getMessage().getContentRaw().split("\\s+");
		
		if (args[0].equalsIgnoreCase(main.prefix + "ud"))
		{
			String term = args[1];
			
			HttpResponse<JsonNode> response = Unirest.get("https://mashape-community-urban-dictionary.p.rapidapi.com/define?term="+term)
					.header("x-rapidapi-host", "mashape-community-urban-dictionary.p.rapidapi.com")
					.header("x-rapidapi-key", "ca2d4917cdmsh8a43388ab8c5278p1e2e89jsn87def1dcf18e")
					.asJson();
			
			JSONArray array = response.getBody().getObject().getJSONArray("list");
			JSONObject obj = array.getJSONObject(0);
			String def = obj.getString("definition");
			String word = obj.getString("word");
			
			EmbedBuilder ud = new EmbedBuilder();
			
			ud.setTitle("Defination of "+word);
			ud.setDescription(def);
			ud.setColor(0x00aa11);
			event.getChannel().sendMessage(ud.build()).queue();
		}
	}

}
