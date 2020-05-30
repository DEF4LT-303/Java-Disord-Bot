package tato_bot;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import tato_bot_commands.Clear;
import tato_bot_commands.Image;
import tato_bot_commands.Info;
import tato_bot_commands.Ping;
import tato_bot_commands.Actions;
import tato_bot_commands.Test;

public class main {
	
	public static JDA jda;
	public static String prefix = "-";
	
	public static void main (String args[]) throws LoginException
	{
		jda = new JDABuilder(AccountType.BOT).setToken("NzExODc0OTkxMDc0ODM2NTYy.XsO_zw.mNtEb6Q4b0EcICqQTprx83Ry_Nk").build();
		
		jda.getPresence().setStatus(OnlineStatus.IDLE);
		//jda.getPresence().setActivity(Activity.watching("Tato"));
		
		jda.addEventListener(new Info());
		jda.addEventListener(new Clear());
		jda.addEventListener(new Ping());
		jda.addEventListener(new Test());
		jda.addEventListener(new Image());
		jda.addEventListener(new Actions());
		
	}

}
