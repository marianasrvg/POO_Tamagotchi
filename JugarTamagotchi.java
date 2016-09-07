package creatingLife;
import javax.swing.JOptionPane;
import creatingLife.Tamagotchi;


public class JugarTamagotchi {
	
	public static void imprimir (Tamagotchi x){
		JOptionPane.showMessageDialog(null, x.talk(), "tu friend<3 "+x.getNombre(), 1);
	}
		
	public static void main(String[] args){
		int menu=0;
		Tamagotchi amiguito;
		int opcion = JOptionPane.showConfirmDialog(null, "¿Quieres ponerle nombre a tu Tamagotchi?");
		if (opcion==0){
			String name = JOptionPane.showInputDialog("Ingresa el nombre de tu tamagotchi:");
			amiguito = new Tamagotchi(name);
		} else if( opcion == 2){
			amiguito = new Tamagotchi(50);
			System.exit(0);
		}
		else{
			int salud = 10;
			try{
				salud = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la salud del tamagotchi:"));
			}catch(Exception e){
				salud = 50;
			}
			amiguito = new Tamagotchi(salud);
		}
		imprimir(amiguito);
		boolean bandera = false;
		do{
			do{
				try{
					bandera = false;
					menu = Integer.parseInt(JOptionPane.showInputDialog("¿Qué quieres hacer con "+amiguito.getNombre()+
						":\n1. Comer1"
						+ "\n2. Jugar"
						+ "\n3. Dormir"
						+ "\n4. Tomar agua"
						+ "\n5. Ir al cine"
						+ "\n6. Preguntale a "+amiguito.getNombre()+ " qué quiere hacer"
						+ "\n7. Ver estado de "+ amiguito.getNombre()
						+ "\n8. Abandonar a "+amiguito.getNombre()+" y salir"));
				} catch (Exception e){
					JOptionPane.showMessageDialog(null, "Ingresa un número válido");
					bandera = true;
				}
				}while(bandera);
			switch(menu){
			case 1:
				JOptionPane.showMessageDialog(null, amiguito.alimentar());
				break;
			case 2:
				JOptionPane.showMessageDialog(null, amiguito.jugar());
				break;
			case 3:
				JOptionPane.showMessageDialog(null, amiguito.dormir());
				break;
			case 4:
				JOptionPane.showMessageDialog(null, amiguito.beber());
				break;
			case 5:
				JOptionPane.showMessageDialog(null, amiguito.verPeli());
				break;
			case 6: 
				amiguito.wannaDo();
				break;
			case 7:
				imprimir(amiguito);
				break;
			case 8:
				JOptionPane.showMessageDialog(null, "Adiós te dice "+amiguito.getNombre());
				System.exit(0);
			default:
				menu = 0;
				break;
			}
		}while(true);
		
		
	}

}
