package campuscreatures.main;
import android.content.Context;
import android.graphics.drawable.Drawable;

import java.util.*;

public class Sprites {
	
	Map<String, Drawable> spriteMap = new HashMap<String, Drawable>();
	
	public Sprites(Context context){
		
		spriteMap.put("Adom",
				context.getResources().getDrawable(R.drawable.adom));
		spriteMap.put("Alogorithmo",
				context.getResources().getDrawable(R.drawable.algorithmo));
		spriteMap.put("Billiken",
				context.getResources().getDrawable(R.drawable.billiken));
		spriteMap.put("Biondi",
				context.getResources().getDrawable(R.drawable.biondi));
		spriteMap.put("Chamber Wolf",
				context.getResources().getDrawable(R.drawable.chamberwolf));
		spriteMap.put("Claire Bear",
				context.getResources().getDrawable(R.drawable.clairebear));
		spriteMap.put("Clueless Freshman",
				context.getResources().getDrawable(R.drawable.cluelessfreshman));
		spriteMap.put("Desi Djinn",
				context.getResources().getDrawable(R.drawable.desidjinn));
		spriteMap.put("Dijkstra",
				context.getResources().getDrawable(R.drawable.dijkstra));
		spriteMap.put("Drushel",
				context.getResources().getDrawable(R.drawable.drushel));
		spriteMap.put("Evinstevin",
				context.getResources().getDrawable(R.drawable.evinstevin));
		spriteMap.put("Fire Fox",
				context.getResources().getDrawable(R.drawable.firefox));
		spriteMap.put("Freemanster",
				context.getResources().getDrawable(R.drawable.freemanster));
		spriteMap.put("Frittsterer",
				context.getResources().getDrawable(R.drawable.frittsterer));
		spriteMap.put("Harrisstowe",
				context.getResources().getDrawable(R.drawable.harrisstowe));
		spriteMap.put("Hebda",
				context.getResources().getDrawable(R.drawable.hebda));
		spriteMap.put("Internet Ixplorer",
				context.getResources().getDrawable(R.drawable.internetexplorer));
		spriteMap.put("Inyourway",
				context.getResources().getDrawable(R.drawable.inyourway));
		spriteMap.put("Kalliongis",
				context.getResources().getDrawable(R.drawable.kalliongis));
		spriteMap.put("Lamarcus",
				context.getResources().getDrawable(R.drawable.lamarcus));
		spriteMap.put("Lescher the Lecturer",
				context.getResources().getDrawable(R.drawable.lescher));
		spriteMap.put("Lovososa",
				context.getResources().getDrawable(R.drawable.lovasosa));
		spriteMap.put("Marcus Taborius",
				context.getResources().getDrawable(R.drawable.markustaborius));
		spriteMap.put("Markist",
				context.getResources().getDrawable(R.drawable.markist));
		spriteMap.put("Parrishable",
				context.getResources().getDrawable(R.drawable.parishable));
		spriteMap.put("Philanthropist",
				context.getResources().getDrawable(R.drawable.philanthropist));
		spriteMap.put("Pied Piper",
				context.getResources().getDrawable(R.drawable.piedpiper));
		spriteMap.put("Pikachu",
				context.getResources().getDrawable(R.drawable.pikachu));
		spriteMap.put("Rasal Goul",
				context.getResources().getDrawable(R.drawable.rasalgoul));
		spriteMap.put("Reinbolt",
				context.getResources().getDrawable(R.drawable.reinbolt));
		spriteMap.put("Roadrunner",
				context.getResources().getDrawable(R.drawable.roadrunner));
		spriteMap.put("Scan Bot",
				context.getResources().getDrawable(R.drawable.scanbot));
		spriteMap.put("Shpeegle",
				context.getResources().getDrawable(R.drawable.shpeegle));
		spriteMap.put("Silverwasser",
				context.getResources().getDrawable(R.drawable.silverwasser));
		spriteMap.put("Srivasta",
				context.getResources().getDrawable(R.drawable.srivasta));
		spriteMap.put("Sudosky",
				context.getResources().getDrawable(R.drawable.sudosky));
		spriteMap.put("Tsauster",
				context.getResources().getDrawable(R.drawable.tsauster));
		spriteMap.put("Turingsteen",
				context.getResources().getDrawable(R.drawable.turingsteen));
		spriteMap.put("Wackerle",
				context.getResources().getDrawable(R.drawable.wackerle));
		spriteMap.put("Weasel Man",
				context.getResources().getDrawable(R.drawable.weaselman));
	}
	
	public Drawable getSprite(String s){
		
		Drawable sprite = spriteMap.get(s);
		
		return sprite;
	}

}
