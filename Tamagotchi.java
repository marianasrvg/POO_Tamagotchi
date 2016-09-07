package creatingLife;

import javax.swing.JOptionPane;

public class Tamagotchi {
	//----------ATRIBUTOS-----------
	private Boolean estado = true;
	private int salud; //1-100
	private int felicidad; //1-10
	private int enojo; //1-10
	private int hambre; //1-10
	private int suenyo; //1-10
	private int sed; //1-10
	private int nigths = 0;
	private String nombre = "Tamagotchi #1"; 
	private 
	int edad;
	
	//-----------MÉTODOS---------------
	//CREACIÓN
	
	public Tamagotchi(int health){
		//creación del objeto Tamagotchi con los parametros
		salud = health;
		felicidad = 5;
		enojo = 5;
		hambre = 8;
		sed = 7;
	}
	
	public Tamagotchi(String name){
		//llamas al constructor y le agregas un nombre con la función setName
		this(50);
		setName(name);
	}
	
	private void setName(String name){
		//asignar nombre
		nombre = name;
	}

	//MODIFICACIONES
/*	private String change(int a,int b,int c,int d,int e,int f){
		String cambios = ("Tu amiguito "+nombre+":\n");
		cambios += "Felicidad:\t"+felicidad+"\t";
		cambios += (a<0)? a:(a==0)? "":("+"+a);
		cambios += "\nSalud:\t"+salud+"\t";
		cambios += (b<0)? b:(b==0)? "":("+"+b);
		cambios += "\nHambre:\t"+hambre+"\t";
		cambios += (c<0)? c:(c==0)? "":("+"+c);
		cambios += "\nEnojo:\t"+enojo+"\t";
		cambios += (d<0)? d:(d==0)? "":("+"+d);
		cambios += "\nSueño:\t"+suenyo+"\t";
		cambios += (e<0)? e:(e==0)? "":("+"+e);
		cambios += "\nSed:\t"+sed+"\t";
		cambios += (f<0)? f:(f==0)? "":("+"+f);
		return cambios;
	}*/

	public void wannaDo(){
		//bipolar te va a definir al azar si quiere o no hablar contigo
		int bipolar = (int)(Math.random()*10);
		//Dependiendo a sus niveles imprime que es más urgente hacer!
		if (bipolar != 5){
			if (hambre>=7){
				JOptionPane.showMessageDialog(null, "ALIMÉNTAME :c", nombre+" quiere...", 0);
			} else if(sed>=7){
				JOptionPane.showMessageDialog(null, "Quiero beber algo :c", nombre+" quiere...", 0);
			} else if(suenyo>=7){
				JOptionPane.showMessageDialog(null, "Y si vamos a dormir?", nombre+" quiere...", 1);
			} else{
				if(bipolar%2==0)
					JOptionPane.showMessageDialog(null, "Hay que JUGAR :D", nombre+"quiere...", 1);
				else
					JOptionPane.showMessageDialog(null, "Vamos al cine!!!", nombre+"quiere...", 1);
			}
		}else{
			JOptionPane.showMessageDialog(null, "YA NO QUIERO QUE ME HABLES!", "Bipolaridad", 0);
			angry(5);
		}
	}
	
	//ACCIONES
	public String dormir(){
		//con un random puede ser que se despierte feliz o triste
		int r = (int)(Math.random()*10);
		if (r%2==0){
			happy(r);
			JOptionPane.showMessageDialog(null, "Amo dormir<3", nombre+" dice", 1);
		}else{
			JOptionPane.showMessageDialog(null, "Amanecí de malas :(", nombre+ " dice", 0);
			r *= -1;
			happy(r);
		}
		health(5);
		hungry(2);
		angry(-1);
		dream(-3);
		thirst(2);
		//cuando pasan más de 3 noches, crece un año
		nigths++;
		if (nigths == 3){
			JOptionPane.showMessageDialog(null, "HAPPY BIRTHDAY "+nombre, "Un año más de vida", 1);
			edad++;
			nigths = 0;
		}
		
		return talk();
	}
	
	public String jugar(){
		//puede ser que jugando se caiga y eso lo pone triste y enojado y le reduce la salud
		int herido = (int)(Math.random()*10);
		if (herido==5){
			happy(-2);
			health(-30);
			hungry(1);
			angry(2);
			thirst(1);
			JOptionPane.showMessageDialog(null, nombre+" se cayó jugando:(");
			return talk();
		} else{
			happy(3);
			health(10);
			hungry(3);
			angry(-1);
			dream(2);
			thirst(3);
			return talk();
		}
	}
	
	public String verPeli(){
		//con un random se decide si le gusta o no la película
		int movie = (int)(Math.random()*10);
		if(movie%2 == 0){
			happy(2);
			hungry(1);
			angry(-1);
			thirst(1);
			JOptionPane.showMessageDialog(null, "YAY movie!", nombre+ " dice", 1);
			return talk();
		}else{
			JOptionPane.showMessageDialog(null, "Esa película no le gusto a "+nombre, ":C", 0);
			happy(-2);
			angry(4);
			return talk();
			
		}
	} 
	
	public String alimentar(){
		//con una funcion de random se decide si la comida esta en buen estado o no
		int calidad = (int)(Math.random()*10);
		if (calidad==5){
			happy(-2);
			health(-30);
			angry(2);
			thirst(2);
			JOptionPane.showMessageDialog(null, nombre+" comió algo en mal estado :( su salud disminuyo");
			return talk();
		} else{
			happy(1);
			health(10);
			hungry(-3);
			angry(-1);
			JOptionPane.showMessageDialog(null, "Yummy!", nombre+ " dice", 1);
			return talk();
		}
	}
	
	public String beber(){
		//cuidado con las bebida que se selecciona a lo random
		int bebida = (int)(Math.random()*10);
		if(bebida==5){
			happy(2);
			health(-15);
			thirst(1);
			JOptionPane.showMessageDialog(null, nombre+" woow! Las bebidas alcohólicas dañan la salud");
			return talk();
		} else{
			happy(1);
			health(10);
			thirst(-3);
			JOptionPane.showMessageDialog(null, "Gracias c:", nombre+ " dice", 1);
			return talk();
		}
	}
		
	//VERIFICAR ESTADO
	private void die(){
		//depiendo los parametros se decide si muere o no
		estado = (salud<=0)? false:true;
		edo("enfermedad");
		estado = (felicidad<=0)? false:true;
		edo("tristeza");
		estado = (hambre>=10)? false:true;
		edo("hambre");
		estado = (sed>=10)? false:true;
		edo("sed");
		estado = (enojo>=10)? false:true;
		edo("coraje");
		estado = (edad>=20)? false:true;
		edo("viej@");
	}
	private void edo(String causa){
		//imprime la causa de muerte
		if (!estado){
			JOptionPane.showMessageDialog(null, nombre+" ha muerto de "+causa+"\nLamento tu pérdida");
			System.exit(0);
		}
		return;
	}
	
	//COMUNICAR SU ESTADO
	public String talk(){
		//regresa las caracteristicas actuals
		String caracteristicas =  nombre+
				"\nTiene "+edad+" años"+
				"\nSalud=\t"+salud+
				"\nFelicidad=\t"+felicidad+
				"\nEnojo=\t"+enojo+
				"\nHambre=\t"+hambre+
				"\nSed=\t"+sed+
				"\nSueño=\t"+suenyo;
		return caracteristicas;
	}
	
	//OBTENER NOMBRE
	public String getNombre(){
		return nombre;
	}
	
	//CAMBIAR ATRIBUTOS
	//SUBIR Y BAJAR NIVELES
	//en cada atributo revisa que no se pase de los parámetros
	private void happy(int x){
		felicidad+=x;
		felicidad = (felicidad>10)? 10:(felicidad<0)?0:felicidad;
		die();
	}
	private void health(int x){
		salud+=x;
		salud = (salud>100)? 100: (salud<0)?0:salud;
		die();
	}
	private void angry(int x){
		enojo+=x;
		enojo = (enojo>10)? 10:(enojo<0)?0:enojo;
		die();
	}
	private void hungry(int x){
		hambre+=x;
		hambre = (hambre>10)?10:(hambre<0)?0:hambre;
		int y = (hambre>5)? 2:-2;
		angry(y);
		die();
	}
	private void dream(int x){
		suenyo+=x;
		suenyo = (suenyo>10)?10:(suenyo<0)?0:suenyo;
		die();
	}
	private void thirst(int x){
		sed += x;
		sed = (sed>10)?10:(sed<0)?0:sed;
		die();
	}
}
