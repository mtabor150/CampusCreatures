package campuscreatures.database;

import java.util.ArrayList;
import java.util.List;

import campuscreatures.location.Location;
import campuscreatures.location.LocationService;
import campuscreatures.location.Zone;

public class MapZones {
/**
 * 	TOTAL: 31 zones
 *  Point 1 - Point 2
 *  Point 1 - Point 3
 *  Point 3 - Point 4
 *  Point 2 - Point 4
 *  
 * DISTRICT:		Point 1						Point 2						Point 3						Point 4
 * SLU 				38.63888, -90.240852		38.636148, -90.241753		38.635671, -90.225681		38.631757, -90.227172
 * 
 * REGION:
 * Adorjan Hall		38.638323, -90.2392			38.638223, -90.238734		38.638059, -90.239281		38.637971, -90.238825		
 * Beracha Hall		38.635922, -90.238154		38.635855, -90.237816		38.635444, -90.238294		38.635390, -90.237993
 * BSC				38.635599, -90.233192		38.635474, -90.232377		38.634648, -90.233401		38.63445, -90.232607
 * Chafeitz Center	38.632545, -90.228032		38.63306, -90.227817		
 * Clocktower		38.636543, -90.236804		38.636673, -90.236758
 * College Church	38.637395, -90.233947		38.637179, -90.232971		38.637014, -90.234052		38.636838, -90.233137
 * B School			38.637779, -90.236086		38.637588, -90.235270		38.637246, -90.236224		38.637025, -90.235338
 * Cupples House	38.636764, -90.235763		38.636911, -90.235708
 * Demattias Hall	38.637661, -90.239811		38.637584, -90.239575
 * DesPeres Hall	38.636381, -90.236793		38.636314, -90.236434		38.635895, -90.236960		38.635838, -90.236597
 * Dubourg Hall		38.636997, -90.234232		38.636773, -90.233129		38.636547, -90.234334		38.635981, -90.233403
 * Griese			38.636077, -90.235002		38.635857, -90.234025		38.635489, -90.235187		38.635315, -90.234234
 * Fitzgerald Hall	38.636743, -90.231195		38.636649, -90.230691		38.636513, -90.231230		38.636408, -90.230750
 * Fusz Hall		38.636663, -90.238116		38.636447, -90.237005		38.636229, -90.238140		38.636229, -90.238140
 * Intramural Field	38.636681, -90.241389		38.636467, -90.240391		38.636211, -90.2451528		38.636018, -90.240563
 * Lecture Halls	38.634941, -90.232123		38.634909, -90.231992		38.634703, -90.232198		38.634684, -90.232080
 * Macelwane Hall	38.634629, -90.232258		38.634504, -90.231610		38.634320, -90.232360		38.634167, -90.231680
 * Marguerite Hall	38.637757, -90.239436		38.637726, -90.239194		38.637364, -90.239589		38.63733, -90.239315
 * McDonnell Doug.	38.636550, -90.230309		38.636299, -90.228984		38.636238, -90.230390		38.635972, -90.229094
 * McGannon Hall	38.638415, -90.238616		38.638202, -90.238068		38.638005, -90238781		38.63795, -90.238189
 * Monsanto Hall	38.635286, -90.231498		38.635193, -90.231074		38.634604, -90.231634		38.634542, -90.231295
 * Pius Library		38.637133, -90.235248		38.637198, -90.234328		38.636515, -90.235441		38.636408, -90.234618
 * Pruellage Hall	38.637307, -90.238712		38.637265, -90.238537
 * Ritter Hall		38.636307, -90.232845		38.636203, -90.232432		38.635798, -90.232569		38.635867, -90.232968	
 * Shannon Hall		38.635353, -90.232038		38.635262, -90.231605		38.634990, -90.232156		38.634931, -90.231718
 * Simon Rec		38.635650, -90.236276		38.635405, -90.235063		38.635090, -90.236485		38.634841, -90.235232
 * Tegeler Hall		38.636888, -90.231852		38.636775, -90.231278		38.636542, -90.231978		38.636458, -90.231431
 * Verhaegen Hall	38.637414, -90.234197		38.637357, -90.233974		38.637054, -90.234334		38.637022, -90.234082
 * Village 1		38.637069, -90.239633		38.636767, -90.238249		38.636515, -90.239880		38.636218, -90.238426
 * Village 2		38.636496, -90.240273		38.636274, -90.239393		38.635964, -90.240466		38.635771, -90.239458
 * Xavier Hall		38.637499, -90.237901		38.637388, -90.237308		38.636979, -90.238102		38.636860, -90.237504					
 */
	Zone zone;
	public ArrayList<Zone> Zones;

	public MapZones () {
		
		Zones = new ArrayList();
		/* Circle based zones */
		
		Zone chafeitz = new Zone("Chafeitz Arena", new Location(38.637265, -90.238537), new Location(38.637265, -90.238537));
		Zones.add(chafeitz);
		
		Zone clockTower = new Zone("Clock Tower", new Location(38.636543, -90.236804), new Location(38.636673, -90.236758));
		Zones.add(clockTower);
		
		Zone cupplesHouse = new Zone("Cupples House", new Location(38.636764, -90.235763), new Location(38.636911, -90.235708));
		Zones.add(cupplesHouse);
		
		Zone deMatt = new Zone("Demattias Hall", new Location(38.637661, -90.239811), new Location(38.637584, -90.239575));
		Zones.add(deMatt);
		
		Zone pruellage = new Zone("Pruellage Hall", new Location(38.637307, -90.238712), new Location(38.637265, -90.238537));
		Zones.add(pruellage);
		
		/* Quadrilateral zones */
		Zone adorjan = new Zone("Adorjan Hall" , new Location(38.638323, -90.2392), new Location(38.638223, -90.238734), 
				 	   new Location(38.638059, -90.239281), new Location(38.637971, -90.238825));		
		Zones.add(adorjan);
		
		Zone beracha = new Zone("Beracha Hall", new Location(38.635922, -90.238154), new Location(38.635855, -90.237816), 
				 	   new Location(38.635444, -90.23829), new Location(38.635390, -90.237993));
		Zones.add(beracha);
		
		Zone bsc = new Zone("BSC", new Location(38.635599, -90.233192), new Location(38.635474, -90.232377),
				   new Location(38.634648, -90.233401), new Location(38.63445, -90.232607));
		Zones.add(bsc);
		
		Zone church = new Zone("College Church", new Location(38.637395, -90.233947), new Location(38.637179, -90.232971), 
					  new Location(38.637014, -90.234052), new Location(38.636838, -90.233137));
		Zones.add(church);
		
		Zone bSchool= new Zone("B School", new Location(38.637779, -90.236086), new Location(38.637588, -90.235270),
					 new Location(38.637246, -90.236224), new Location(38.637025, -90.235338));
		Zones.add(bSchool);
		
		Zone dePeres= new Zone("DesPeres Hall",	new Location(38.636381, -90.236793), new Location(38.636314, -90.236434),
					  new Location(38.635895, -90.236960), new Location(38.635838, -90.236597));
		Zones.add(dePeres);
		
		Zone dubourg = new Zone("Dubourg Hall", new Location(38.636997, -90.234232), new Location(38.636773, -90.233129),
					   new Location(38.636547, -90.234334), new Location(38.635981, -90.233403));
		Zones.add(dubourg);
		
		Zone griese = new Zone("Griesedieck Hall", new Location(38.636077, -90.235002), new Location(38.635857, -90.234025),
					  new Location(38.635489, -90.235187), new Location(38.635315, -90.234234));
		Zones.add(griese);
		
		Zone fitz = new Zone("Fitzgerald Hall", new Location(38.636743, -90.231195), new Location(38.636649, -90.230691),
					new Location(38.636513, -90.231230), new Location(38.636408, -90.230750));
		Zones.add(fitz);
		
		Zone fusz = new Zone("Fusz Hall", new Location(38.636663, -90.238116), new Location(38.636447, -90.237005), 
					new Location(38.636229, -90.238140), new Location(38.636229, -90.238140));
		Zones.add(fusz);
		
		Zone intraField = new Zone("Intramural Field", new Location(38.636681, -90.241389), new Location(38.636467, -90.240391),
						  new Location(38.636211, -90.2451528), new Location(38.636018, -90.240563));
		Zones.add(intraField);
		
		Zone lectureHalls = new Zone("Lecture Halls", new Location(38.634941, -90.232123), new Location(38.634909, -90.231992),
							new Location(38.634703, -90.232198), new Location(38.634684, -90.232080));
		Zones.add(lectureHalls);
		
		Zone macelwane = new Zone("Macelwane Hall",	new Location(38.634629, -90.232258), new Location(38.634504, -90.231610),
						 new Location(38.634320, -90.232360), new Location(38.634167, -90.231680));
		Zones.add(macelwane);
		
		Zone marg = new Zone("Marguerite Hall",	new Location(38.637757, -90.239436), new Location(38.637726, -90.239194),
					new Location(38.637364, -90.239589), new Location(38.63733, -90.239315));
		Zones.add(marg);
		
		Zone mcdonnell = new Zone("McDonnell Douglas Hall",	new Location(38.636550, -90.230309), new Location(38.636299, -90.228984), 
						 new Location(38.636238, -90.230390), new Location(38.635972, -90.229094));
		Zones.add(mcdonnell);
		
		Zone mcgannon = new Zone("McGannon Hall", new Location(38.638415, -90.238616), new Location(38.638202, -90.238068),
						new Location(38.638005, -90238781), new Location(38.63795, -90.238189));
		Zones.add(mcgannon);
		
		Zone monsanto = new Zone("Monsanto Hall", new Location(38.635286, -90.231498), new Location(38.635193, -90.231074),
						new Location(38.634604, -90.231634), new Location(38.634542, -90.231295));
		Zones.add(monsanto);
		
		Zone library = new Zone("Pius Library", new Location(38.637133, -90.235248), new Location(38.637198, -90.234328), 
					   new Location(38.636515, -90.235441), new Location(38.636408, -90.234618));
		Zones.add(library);
		
		Zone ritter = new Zone("Ritter Hall", new Location(38.636307, -90.232845), new Location(38.636203, -90.232432), 
					  new Location(38.635798, -90.232569), new Location(38.635867, -90.232968));
		Zones.add(ritter);
		
		Zone shannon = new Zone("Shannon Hall", new Location(38.635353, -90.232038), new Location(38.635262, -90.231605), 
					   new Location(38.634990, -90.232156),	new Location(38.634931, -90.231718));
		Zones.add(shannon);
		
		Zone simonRec = new Zone("Simon Rec", new Location(38.635650, -90.236276), new Location(38.635405, -90.235063), 
						new Location(38.635090, -90.236485), new Location(38.634841, -90.235232));
		Zones.add(simonRec);
		
		Zone tegeler = new Zone("Tegeler Hall", new Location(38.636888, -90.231852), new Location(38.636775, -90.231278),	
					   new Location(38.636542, -90.231978), new Location(38.636458, -90.231431));
		Zones.add(tegeler);
		
		Zone verhaegen = new Zone("Verhaegen Hall",	new Location(38.637414, -90.234197), new Location(38.637357, -90.233974), 
						 new Location(38.637054, -90.234334), new Location(38.637022, -90.234082));
		Zones.add(verhaegen);
		
		Zone village1 = new Zone("Village 1", new Location(38.637069, -90.239633), new Location(38.636767, -90.238249),
						new Location(38.636515, -90.239880), new Location(38.636218, -90.238426));
		Zones.add(village1);
		
		Zone village2 = new Zone("Village 2", new Location(38.636496, -90.240273), new Location(38.636274, -90.239393),
						new Location(38.635964, -90.240466), new Location(38.635771, -90.239458));
		Zones.add(village2);
		
		Zone xavier = new Zone("Xavier Hall", new Location(38.637499, -90.237901), new Location(38.637388, -90.237308), 
					  new Location(38.636979, -90.238102), new Location(38.636860, -90.237504));
		Zones.add(xavier);
		

		
	}
		
	public Object getMapZone(){
		return this.zone;
	}
	

}
