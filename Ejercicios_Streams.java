package ejercicios_streams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Ejercicios_Streams {

	public static void main(String[] args) {
		List<Curso> cursos = new ArrayList<>();
		cursos.add(new Curso("Cursos profesional de Java", 6.5f, 50, 200 ));
		cursos.add(new Curso("Cursos profesional de Python", 8.5f, 60, 800 ));
		cursos.add(new Curso("Cursos profesional de DB", 4.5f, 70, 700 ));
		cursos.add(new Curso("Cursos profesional de Android", 7.5f, 10, 400 ));
		cursos.add(new Curso("Cursos profesional de Escritura", 1.5f, 10, 300 ));
	
		long cantidad = cursos.stream()
			    .filter(c -> c.getDuracion() > 5)
			    .count();                        
			System.out.println("Cursos > 5h: " + cantidad);
			
		long cantidadMenor = cursos.stream()
				.filter(c -> c.getDuracion() < 2)
				.count();
		System.out.println("Cursos < 2h: " + cantidad);
		
		
		List <String> cantidadVideosList = cursos.stream() // Lista de titulos con > 50 videos
				.filter(c -> c.getVideos() > 50)
				.map(c -> c.getTitulo())
				.toList();
		
		ArrayList <String> cantidadVideosArrayListCollector = cursos.stream() // ArrayList de titulos > 50 con el new al final del collect
				.filter(c -> c.getDuracion() > 50)
				.map(c -> c.getTitulo())
				.collect(Collectors.toCollection(ArrayList::new));
		
		ArrayList<String> cantidadVideosenNewArrayList = new ArrayList<> ( //  ArrayList de titulos > 50 con el new al principio
				cursos.stream()
				.filter(c -> c.getVideos() > 50)
				.map(c -> c.getTitulo())
				.toList());
		
		List <String> listaDeTres = cursos.stream() // Lista de los 3 de mayor duración
				.sorted(Comparator.comparing(Curso::getDuracion).reversed())
				.limit(3)
				.map(Curso::getTitulo)
				.toList();
		
		cursos.stream() // Imprimir los 3 de mayor duración
			.sorted(Comparator.comparing(Curso::getDuracion).reversed())
			.limit(3)
			.map(Curso::getTitulo)
			.forEach(System.out::println);
		
		double duracionTotal = cursos.stream() // Duracion de todos los cursos
				.mapToDouble(Curso::getDuracion)
				.sum();
		System.out.println("La duración total de todos los cursos es: " + duracionTotal);
		
		// Mostrar en consola todos aquellos titulos que superen el promedio en cuanto a duración se refiere.
		
		double promedio = cursos.stream() // Primero el promedio
				.mapToDouble(Curso::getDuracion)
				.average()
				.orElse(0.0);
		
		cursos.stream()
			.filter(c -> c.getDuracion() > promedio)
			.map(Curso::getTitulo) // Podría usar el -> pero como no voy a operar ni condicionar solo extraer un dato es más legible así
			.forEach(System.out::println);
		
		//Mostrar en consola la duración de todos aquellos cursos que tengan una cantidad de alumnos inscritos menor a 500.
		cursos.stream()
			.filter(c -> c.getAlumnos() < 500)
			.mapToDouble(Curso::getDuracion)
			.forEach(System.out::println);	
		//Obtener el curso con mayor duración.
		String tituloMayorduracion = cursos.stream()
			.max(Comparator.comparing(Curso::getDuracion))
			.map(Curso::getTitulo)
			.orElse("No hay");
			
		
		
		
		//Crear una lista de Strings con todos los titulos de los cursos.
		List <String> listaDeTitulos = cursos.stream()
			.map(Curso::getTitulo)
			.toList();
	
	}

}
