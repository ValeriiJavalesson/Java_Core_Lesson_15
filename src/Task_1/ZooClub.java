package Task_1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class ZooClub {
	public static void main(String[] args) {
		String input;
		Scanner scan = new Scanner(System.in);
		Map<Person, List<Pet>> map = new HashMap();
		
		map.put(new Person("Andrew", 15), new ArrayList<>());
		map.put(new Person("Tolik", 23), new ArrayList<>());
		map.put(new Person("Anna", 18), new ArrayList<>());

		Iterator<Entry<Person, List<Pet>>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<Person, List<Pet>> next = iterator.next();
			if (next.getKey().getPersonName().equals("Andrew")) {
				next.getValue().add(new Pet("Bob", "Dog"));
				next.getValue().add(new Pet("Rex", "Dog"));
				next.getValue().add(new Pet("Tinny", "Mouse"));
				next.getValue().add(new Pet("Axel", "Dog"));
				next.getValue().add(new Pet("Blaze", "Cat"));
				Collections.sort(next.getValue());
			}
			if (next.getKey().getPersonName().equals("Anna")) {
				next.getValue().add(new Pet("Micle", "Dog"));
				next.getValue().add(new Pet("Ron", "Dog"));
				next.getValue().add(new Pet("Jerry", "Mouse"));
				next.getValue().add(new Pet("Pol", "Dog"));
				next.getValue().add(new Pet("Tom", "Cat"));
				Collections.sort(next.getValue());
			}
			if (next.getKey().getPersonName().equals("Tolik")) {
				next.getValue().add(new Pet("Wind", "Dog"));
				next.getValue().add(new Pet("Jack", "Dog"));
				next.getValue().add(new Pet("Mini", "Mouse"));
				next.getValue().add(new Pet("Winni", "Dog"));
				next.getValue().add(new Pet("Olya", "Cat"));
				Collections.sort(next.getValue());
			}
		}
		while (true) {
			showMenu();
			input = scan.next();
			switch (input) {
			case "0": {
				System.exit(0);
			}
			case "1": {
				System.out.println("Введіть ім’я нового учасника клубу");
				String name = scan.next();
				System.out.println("Введіть вік нового учасника клубу");
				int age = scan.nextInt();
				Person newPerson = new Person(name, age);
				if (!isPresent(map, newPerson)) {
					map.put(newPerson, new ArrayList<>());
					System.out.println("Новий учасник " + name + " успішно доданий!");
					break;
				}
				System.out.println("Такий учасник уже існує");
				break;
			}
			case "2": {
				System.out.println("Введіть ім’я учасника клубу, до якого потрібно додати тваринку");
				showAllPerson(map);
				String name = scan.next();
				addPetToPerson(name, map);
				break;
			}
			case "3": {
				System.out.println("Введіть ім’я учасника клубу, в якого потрібно видалити тваринку");
				String name = scan.next();
				if (removePetFromPerson(map, name)) {
					System.out.println("Тваринку видалено!");
					break;
				} else
					System.out.println("Такої тваринки не існує!");
				break;
			}
			case "4": {
				System.out.println("Введіть ім’я учасника клубу, якого потрібно видалити");
				String name = scan.next();
				if(removePerson(map, name)) System.out.println("Учасник " + name + " успішно видалений!");
				else System.out.println("Такого учасника не знайдено.");
				break;
			}
			case "5": {
				System.out.println("Введіть тип тваринки, який потрібно видалити всюди:");
				String type = scan.next();
				if (removePetType(map, type))
					System.out.println("Тип " + type + " видалено!");
				else
					System.out.println("Не знайдено тварин типу " + type);
				break;
			}
			case "6": {
				showAllZoo(map);
				break;
			}
			}
		}
	}

	public static void showMenu() {
		System.out.println("");
		System.out.println("1-Додати учасника клубу");
		System.out.println("2-Додати тваринку до учасника клубу");
		System.out.println("3-Видалити тваринку з учасника клубу");
		System.out.println("4-Видалити учасника з клубу");
		System.out.println("5-Видалити конкретну тваринку зі всіх власників");
		System.out.println("6-Вивести на екран зооклуб");
		System.out.println("0-Вийти з програми");
	}

	public static void showAllPerson(Map map) {
		Iterator<Entry<Person, List<Pet>>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<Person, List<Pet>> next = iterator.next();
			Person person = next.getKey();
			System.out.println(person.getPersonName());
		}
	}

	public static boolean addPetToPerson(String name, Map map) {
		Iterator<Entry<Person, List<Pet>>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<Person, List<Pet>> next = iterator.next();
			Person person = next.getKey();
			if (person.getPersonName().equalsIgnoreCase(name)) {
				List<Pet> list = next.getValue();
				Scanner scan = new Scanner(System.in);
				System.out.println("Введіть ім’я тваринки:");
				String petName = scan.next();
				System.out.println("Введіть тип тваринки:");
				String petType = scan.next();
				list.add(new Pet(petName, petType));
				System.out.println("Тваринка додана!");
				Collections.sort(list);
				return true;
			}
		}
		System.out.println("Такого учасника не існує!");
		return false;
	}
/**
 * Метод приймає Map та Person та порівнює Person з існуючими в Map, повертає false, якщо
 * Person з такими ім’ям та віком вже існують.
 * */
	public static boolean isPresent(Map map, Person newPerson) {
		Iterator<Entry<Person, List<Pet>>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<Person, List<Pet>> next = iterator.next();
			Person person = next.getKey();
			if (person.equals(newPerson))
				return true;
		}
		return false;
	}

	public static void showAllZoo(Map map) {
		Iterator<Entry<Person, List<Pet>>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<Person, List<Pet>> next = iterator.next();
			System.out.println("---------------------");
			System.out.println(next.getKey().getPersonName() +"("+next.getKey().getAge()+")" +":");
			Iterator<Pet> iterator1 = next.getValue().iterator();
			while (iterator1.hasNext()) {
				Pet pet = iterator1.next();
				System.out.printf("%-10s %10s\n", pet.getPetName(), pet.getPetType());
			}
			
		}
		if(!map.isEmpty())
		System.out.println("---------------------");
		else System.out.println("Зооклуб порожній.");
	}

	public static boolean removePerson(Map map, String name) {
		Iterator<Entry<Person, List<Pet>>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<Person, List<Pet>> next = iterator.next();
			Person person = next.getKey();
			if(person.getPersonName().equalsIgnoreCase(name)){
				iterator.remove();
				return true;
			}
		}
		return false;
	}

	public static boolean removePetFromPerson(Map map, String name) {
		Iterator<Entry<Person, List<Pet>>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<Person, List<Pet>> next = iterator.next();
			if (next.getKey().getPersonName().equalsIgnoreCase(name)) {
				System.out.println("Введіть ім’я тваринки, яку потрібно видалити:");
				Scanner scan = new Scanner(System.in);
				String petName = scan.next();
				Iterator<Pet> iterator2 = next.getValue().iterator();
				while (iterator2.hasNext()) {
					Pet pet = iterator2.next();
					if (pet.getPetName().equalsIgnoreCase(petName)) {
						iterator2.remove();
						return true;
					}
				}
			}
		}
		return false;

	}

	public static boolean removePetType(Map map, String type) {
		boolean flag = false;
		Iterator<Entry<Person, List<Pet>>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<Person, List<Pet>> next = iterator.next();
			Iterator<Pet> iterator1 = next.getValue().iterator();
			while (iterator1.hasNext()) {
				Pet pet = iterator1.next();
				if (type.equalsIgnoreCase(pet.getPetType())) {
					iterator1.remove();
					flag = true;
				}
			}
		}
		return flag;
	}
}
