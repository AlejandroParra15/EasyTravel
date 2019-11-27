package threads;

import ui.EasyTravelController;
import ui.TravelMap;

public class LoadMap extends Thread {

	EasyTravelController easyTravelController;
	TravelMap travelMap;
	String msg;

	public LoadMap(EasyTravelController etc, TravelMap map, String message) {
		easyTravelController = etc;
		travelMap = map;
		msg = message;
	}

	@Override
	public void run() {
		easyTravelController.addMessage("Espere mientras se genera "+ msg);
		try {
			for (int i = 0; i < 5; i++) {
				sleep(1000);
				easyTravelController.addMessage(".");
			}
			if(easyTravelController.getMessage().equals("la ruta ")) {
				easyTravelController.addMessage(" | Ruta Creada");
				easyTravelController.generatePath(travelMap);
				sleep(2000);
				easyTravelController.setMessage("");
			}else {
				easyTravelController.addMessage(" | Mapa creado");
				easyTravelController.drawPoints(travelMap);
				sleep(2000);
				easyTravelController.setMessage("");
			}
			
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		
	}

}
