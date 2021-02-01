package fr.serenn.dhb.colors;

import fr.serenn.dhb.teams.DHBTeam;

public class JavaColors {
	
	public static final String Reset = "\u001B[0m";
	public static final String Bright = "\u001B[1m";
	public static final String EndBright = "\u001B[21m";
	
	public static final String Black = "\u001B[30m";
	public static final String Red = "\u001B[31m";
	public static final String Green = "\u001B[32m";
	public static final String Yellow = "\u001B[33m";
	public static final String Blue = "\u001B[34m";
	public static final String Magenta = "\u001B[35m";
	public static final String Cyan = "\u001B[36m";
	public static final String White = "\u001B[37m";
	
	public static final String BlackBG = "\u001B[40m";
	public static final String RedBG = "\u001B[41m";
	public static final String GreenBG = "\u001B[42m";
	public static final String YellowBG = "\u001B[43m";
	public static final String BlueBG = "\u001B[44m";
	public static final String MagentaBG = "\u001B[45m";
	public static final String CyanBG = "\u001B[46m";
	public static final String WhiteBG = "\u001B[47m";
	
	public static String getTeamColor(DHBTeam t) {
		
		String color = null;
		
		switch (t.color) {
		
		case RED :
			color = "§c";
			break;
		case YELLOW :
			color = "§e";
			break;
		case GREEN :
			color = "§a";
			break;
		case BLUE :
			color = "§9";
			break;
		case BLACK :
			color = "§0";
			break;
		default :
			color = "";
			break;			
		}
		
		return color;
	}
	
}
