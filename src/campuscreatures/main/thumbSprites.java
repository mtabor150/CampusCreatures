package campuscreatures.main;
import android.content.Context;
import android.graphics.drawable.Drawable;

import java.util.*;

public class thumbSprites {
	
	Map<String, Drawable> spriteMap = new HashMap<String, Drawable>();
	
	public thumbSprites(Context context){
		System.out.println("SPAWNING ALL SPRITES");
		spriteMap.put("Adom",
				context.getResources().getDrawable(R.drawable.adomthumb));
		spriteMap.put("Alogorithmo",
				context.getResources().getDrawable(R.drawable.algorithmothumb));
		spriteMap.put("Billiken",
				context.getResources().getDrawable(R.drawable.billikenthumb));
		spriteMap.put("Biondi",
				context.getResources().getDrawable(R.drawable.biondithumb));
		spriteMap.put("Chamber Wolf",
				context.getResources().getDrawable(R.drawable.chamberwolfthumb));
		spriteMap.put("Clair Bear",
				context.getResources().getDrawable(R.drawable.clairebearthumb));
		spriteMap.put("Clueless Freshman",
				context.getResources().getDrawable(R.drawable.cluelessfreshmanthumb));
		spriteMap.put("Desi Djinn",
				context.getResources().getDrawable(R.drawable.desidjinnthumb));
		spriteMap.put("Dijkstra",
				context.getResources().getDrawable(R.drawable.dijkstrathumb));
		spriteMap.put("Drushel",
				context.getResources().getDrawable(R.drawable.drushelthumb));
		spriteMap.put("Evinstevin",
				context.getResources().getDrawable(R.drawable.evinstevinthumb));
		spriteMap.put("Fire Fox",
				context.getResources().getDrawable(R.drawable.firefoxthumb));
		spriteMap.put("Freemanster",
				context.getResources().getDrawable(R.drawable.freemansterthumb));
		spriteMap.put("Frittsterer",
				context.getResources().getDrawable(R.drawable.frittstererthumb));
		spriteMap.put("Harrisstowe",
				context.getResources().getDrawable(R.drawable.harrisstowethumb));
		spriteMap.put("Hebda",
				context.getResources().getDrawable(R.drawable.hebdathumb));
		spriteMap.put("Internet Ixplorer",
				context.getResources().getDrawable(R.drawable.internetexplorerthumb));
		spriteMap.put("Inyourway",
				context.getResources().getDrawable(R.drawable.inyourwaythumb));
		spriteMap.put("Kalliongis",
				context.getResources().getDrawable(R.drawable.kalliongisthumb));
		spriteMap.put("Lamarcus",
				context.getResources().getDrawable(R.drawable.lamarcusthumb));
		spriteMap.put("Lescher the Lecturer",
				context.getResources().getDrawable(R.drawable.lescherthumb));
		spriteMap.put("Lovasosa",
				context.getResources().getDrawable(R.drawable.lovasosathumb));
		spriteMap.put("Marcus Taborius",
				context.getResources().getDrawable(R.drawable.markustaboriusthumb));
		spriteMap.put("Markist",
				context.getResources().getDrawable(R.drawable.markistthumb));
		spriteMap.put("Parrishable",
				context.getResources().getDrawable(R.drawable.parishablethumb));
		spriteMap.put("Philanthropist",
				context.getResources().getDrawable(R.drawable.philanthropistthumb));
		spriteMap.put("Pied Piper",
				context.getResources().getDrawable(R.drawable.piedpiperthumb));
		spriteMap.put("Pikachu",
				context.getResources().getDrawable(R.drawable.pikachuthumb));
		spriteMap.put("Rasal Ghul",
				context.getResources().getDrawable(R.drawable.rasalgoulthumb));
		spriteMap.put("Reinbolt",
				context.getResources().getDrawable(R.drawable.reinboltthumb));
		spriteMap.put("Roadrunner",
				context.getResources().getDrawable(R.drawable.roadrunnerthumb));
		spriteMap.put("Scan Bot",
				context.getResources().getDrawable(R.drawable.scanbotthumb));
		spriteMap.put("Shpeegle",
				context.getResources().getDrawable(R.drawable.shpeeglethumb));
		spriteMap.put("Silverwasser",
				context.getResources().getDrawable(R.drawable.silverwasserthumb));
		spriteMap.put("Srivasta",
				context.getResources().getDrawable(R.drawable.srivastathumb));
		spriteMap.put("Sudowsky",
				context.getResources().getDrawable(R.drawable.sudoskythumb));
		spriteMap.put("Tsauster",
				context.getResources().getDrawable(R.drawable.tsausterthumb));
		spriteMap.put("Turingsteen",
				context.getResources().getDrawable(R.drawable.turingsteenthumb));
		spriteMap.put("Wackerle",
				context.getResources().getDrawable(R.drawable.wackerlethumb));
		spriteMap.put("Weasel Man",
				context.getResources().getDrawable(R.drawable.weaselmanthumb));
		System.out.println("SPAWNING ALL SPRITES");
	}
	
	public Drawable getthumbSprite(String s){
		System.out.println(s);
		Drawable sprite = spriteMap.get(s);
		
		return sprite;
	}

}

