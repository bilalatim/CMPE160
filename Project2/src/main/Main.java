

package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import containers.*;

import ports.Port;
import ships.Ship;


public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		

		Scanner in = new Scanner(new File(args[0]));
		PrintStream out = new PrintStream(new File(args[1]));
		
		
		Ship[] ships = new Ship[9999];
		Port[] ports = new Port[9999];
		Container[] containers = new Container[9999];
		
		int ShipID = 0;
		int PortID = 0;
		int ContainerID = 0;
		
		int choose;
		int N= in.nextInt();
		int portlocID;
		int shiplocID;
		int contlocID;
		int CWeight;
		String type;

		
		int maxweight;
		int maxnumcon;
		int maxnumheavy;
		int maxnumref;
		int maxnumliq;
		double fuelconsum;
		
		double X;
		double Y;
		
		for (int i=0; i<N; i++) {
					
					choose = in.nextInt();
					
					switch (choose) {
						
					case 1:
						portlocID = in.nextInt();
						CWeight = in.nextInt();
						if (!in.hasNextInt()) {
							type = in.next();
							if (type.equals("R")) {
								containers[ContainerID] = new RefrigeratedContainer (ContainerID, CWeight);
									
							}
							
							else if (type.equals("L")) {
								containers[ContainerID] = new LiquidContainer (ContainerID, CWeight);
								
							}
						}
						
						else if (CWeight>3000) {
							containers[ContainerID] = new HeavyContainer (ContainerID, CWeight);
							
						}
						else {
							containers[ContainerID] = new BasicContainer (ContainerID, CWeight);
							
						}
						ports[portlocID].get(containers[ContainerID]);
						ContainerID = ContainerID + 1;
						
						
						break;
					case 2:
						portlocID = in.nextInt();
						maxweight = in.nextInt();
						maxnumcon = in.nextInt();
						maxnumheavy = in.nextInt();
						maxnumref = in.nextInt();
						maxnumliq = in.nextInt();
						fuelconsum = in.nextDouble();
						
						ships[ShipID] = new Ship(ShipID, ports[portlocID], maxweight, maxnumcon, maxnumheavy, maxnumref, maxnumliq, fuelconsum);
						ports[portlocID].incomingShip(ships[ShipID]);
						ShipID = ShipID + 1;
						
						break;
						
					case 3:
						X = in.nextDouble();
						Y = in.nextDouble();
						
						ports[PortID] = new Port(PortID, X, Y);
						PortID = PortID + 1;
					
						break;
						
					case 4:
						shiplocID = in.nextInt();
						contlocID = in.nextInt();
						
						if (ships[shiplocID].load(containers[contlocID])) {
							ships[shiplocID].currentPort.give(containers[contlocID]);
						}
						break;
						
					case 5:
						shiplocID = in.nextInt();
						contlocID = in.nextInt();
						if (ships[shiplocID].unLoad(containers[contlocID])) {
							ships[shiplocID].currentPort.get(containers[contlocID]);
						}
						break;
						
					case 6:
						shiplocID = in.nextInt();
						portlocID = in.nextInt();
						
						if(ships[shiplocID].sailTo(ports[portlocID])) {
							ships[shiplocID].currentPort.outgoingShip(ships[shiplocID]);
							ships[shiplocID].changePort(ports[portlocID]);
							ships[shiplocID].currentPort.incomingShip(ships[shiplocID]);
						}
						
						break;
						
					case 7:
						shiplocID = in.nextInt();
						fuelconsum = in.nextDouble();
						ships[shiplocID].reFuel(fuelconsum);
						break;
					}
		}
		
		
		for (Port p : ports) {
			if (p == null) {
				break;
			}
			p.getContainers();
			out.print("Port " + p.ID + ": (" );
			out.printf("%.2f", p.X);
			out.print(", ");
			out.printf("%.2f", p.Y);
			out.println(")");
			
			
			String Basic = "  BasicContainer:";
			String Refrigerated = "  RefrigeratedContainer:";
			String Heavy = "  HeavyContainer:";
			String Liquid = "  LiquidContainer:";
			
			
			for (Container c : p.containers) {
				
				
				
				if(c instanceof RefrigeratedContainer) {
					Refrigerated = Refrigerated + " " + c.ID;
				}
				else if(c instanceof LiquidContainer) {
					Liquid = Liquid + " " + c.ID;
				}
				else if(c instanceof BasicContainer) {
					Basic= Basic + " " + c.ID;
					
				}
				else if(c instanceof HeavyContainer) {
					Heavy = Heavy + " " + c.ID;
				}
			}
			if (Basic != "  BasicContainer:") out.println(Basic);
			if (Heavy != "  HeavyContainer:") out.println(Heavy);
			if (Refrigerated != "  RefrigeratedContainer:") out.println(Refrigerated);
			if (Liquid != "  LiquidContainer:") out.println(Liquid);
			
			
			
			p.getCurrent();	
			for (Ship s : p.current) {
				
				out.print("  Ship " +s.ID + ": ");
				out.printf("%.2f", s.fuel);
				out.println();
				
				Basic = "    BasicContainer:";
				Refrigerated = "    RefrigeratedContainer:";
				Heavy = "    HeavyContainer:";
				Liquid = "    LiquidContainer:";
				for (Container c : s.currentContainers) {
					
					
					
					if(c instanceof RefrigeratedContainer) {
						Refrigerated = Refrigerated + " " + c.ID;
					}
					else if(c instanceof LiquidContainer) {
						Liquid = Liquid + " " + c.ID;
					}
					else if(c instanceof BasicContainer) {
						Basic= Basic + " " + c.ID;
						
					}
					else if(c instanceof HeavyContainer) {
						Heavy = Heavy + " " + c.ID;
					}
					
				}
				if (Basic != "    BasicContainer:") out.println(Basic);
				if (Heavy != "    HeavyContainer:") out.println(Heavy);
				if (Refrigerated != "    RefrigeratedContainer:") out.println(Refrigerated);
				if (Liquid != "    LiquidContainer:") out.println(Liquid);
			}
			
			}
			
		
		in.close();
		out.close();
	}
}
