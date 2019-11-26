package threads;

import ui.EasyTravelController;
import ui.TravelMap;

public class LoadMap extends Thread {

	EasyTravelController easyTravelController;
	TravelMap travelMap;

	public LoadMap(EasyTravelController etc, TravelMap map) {
		easyTravelController = etc;
		travelMap = map;
	}

	@Override
	public void run() {
		easyTravelController.addMessage("Espere mientras se genera la ruta ");
		try {
			for (int i = 0; i < 5; i++) {
				sleep(1000);
				easyTravelController.addMessage(".");
			}
			easyTravelController.addMessage(" | Ruta Creada");
			easyTravelController.generatePath(travelMap);
			sleep(2000);
			easyTravelController.setMessage("");
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		
	}

}
