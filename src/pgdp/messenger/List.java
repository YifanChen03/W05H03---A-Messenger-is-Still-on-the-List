package pgdp.messenger;

import java.time.*;

public class List {
	private ListElement head;
	private ListElement tail;
	private int size;

	public boolean isEmpty() {
		return head == null;
	}

	/** Fügt die übergebene 'message' am übergebenen 'index' ein.
	 *  Wenn der 'index' out of bounds liegt oder die 'message' 'null' ist, geschieht nichts.
	 * @param index Ein beliebiger Integer
	 * @param message Eine beliebige Message-Referenz
	 */
	public void insertAt(int index, Message message) {
		if (index > size || index < 0 || message == null) {
			return;
		}
		if (head == null) {
			head = tail = new ListElement(message, null);
		} else if (index == 0) {
			head = new ListElement(message, head);
		} else if (index == size) {
			add(message);
			return;
		} else {
			ListElement prev = null;
			ListElement current = head;
			for (int i = 0; i < index; i++) {
				prev = current;
				current = current.getNext();
			}
			prev.setNext(new ListElement(message, current));
		}
		size++;
	}

	/** Fügt die übergebene 'message' am Ende dieser Liste hinzu. Wenn die Message 'null' ist, geschieht nichts.
	 * @param message Eine beliebige Message-Referenz
	 */
	public void add(Message message) {
		if (message == null) {
			return;
		}
		if (tail == null) {
			head = tail = new ListElement(message, null);
		} else {
			tail.setNext(new ListElement(message, null));
			tail = tail.getNext();
		}
		size++;
	}

	/** Entfernt die übergebene 'message' (das konkrete Objekt) aus der Liste.
	 *  Wenn es nicht enthalten ist (oder 'message == null' ist), geschieht nichts.
	 * @param message Eine beliebige Message-Referenz
	 */
	public void delete(Message message) {
		ListElement prev = null;
		ListElement current = head;
		while (current != null) {
			if (current.getMessage() == message) {
				if (prev == null) {
					head = head.getNext();
				} else {
					prev.setNext(current.getNext());
					if (current.getNext() == null) {
						tail = prev;
					}
				}
				size--;
				return;
			}
			prev = current;
			current = current.getNext();
		}
	}

	/** Gibt die aktuelle Größe dieser Liste zurück.
	 * @return Die Größe dieser Liste
	 */
	public int size() {
		return size;
	}

	/** Gibt die Message an der 'index'-ten Stelle dieser Liste zurück.
	 *  Wenn der übergebene 'index' out of bounds liegt, wird 'null' zurückgegeben.
	 * @param index Ein beliebiger Integer
	 * @return Die gefundene Message oder 'null'
	 */
	public Message getByIndex(int index) {
		if (index >= size || index < 0) {
			return null;
		}
		ListElement current = head;
		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}
		return current.getMessage();
	}

	/** Gibt die Message mit der übergebenen ID zurück, falls sie sich in der Liste befindet, 'null' sonst.
	 * @param id Ein beliebiger Long
	 * @return Die gefundene Message oder 'null'
	 */
	public Message getByID(long id) {
		// TODO: Implementiere diese Methode
		ListElement current = head;

		while (current != tail) {
			if (current.getMessage().getId() == id) {
				return current.getMessage();
			}
			current = current.getNext();
		}
		return null;
	}

	/** Merged die übergebenen Listen in eine große Liste. Diese wird dann zurückgegeben.
	 * @param input Ein beliebiges Array von beliebigen Listen
	 * @return Die gemergte Liste
	 */
	public static List megaMerge(List... input) {
		// TODO: Implementiere diese Methode
		int n = 0;
		if (input == null || input.length == 0) {
			return new List();
		}

		for (int i = 0; i < input.length - 1; i++) {
			input[i + 1] = merge(input[i], input[i + 1]);
			n = i;
		}
		return input[n + 1];
	}

	/** Gibt eine neue Liste zurück, die alle Messages dieser Liste, deren Time-Stamp zwischen 'start' (inklusive)
	 *  und 'end' (exklusive) liegt, in der gleichen Reihenfolge enthält.
	 * @param start Ein beliebiges LocalDateTime-Objekt
	 * @param end Ein beliebiges LocalDateTime-Objekt
	 * @return Die gefilterte Liste
	 */
	public List filterDays(LocalDateTime start, LocalDateTime end) {
		// TODO: Implementiere diese Methode
		List output = new List();
		ListElement current = head;
		//falls eines der beiden Parameter null oder end liegt vor start
		if (start == null || end == null || end.isBefore(start)) {
			return new List();
		}

		//alle anderen listenelemnte hinzufügen
		while (current != null) {
			if ((current.getMessage().getTimestamp().isAfter(start) ||
					current.getMessage().getTimestamp().isEqual(start))
					&& current.getMessage().getTimestamp().isBefore(end)) {
				output.add(current.getMessage());
			}
			current = current.getNext();
		}
		return output;
	}

	/** Gibt eine neue Liste zurück, die alle Messages dieser Liste, deren Nutzer gleich 'user' ist, enthält.
	 * @param user Eine beliebige User-Referenz
	 * @return Die gefilterte Liste
	 */
	public List filterUser(User user) {
		// TODO: Implementiere diese Methode
		List output = new List();
		ListElement current = head;

		if (user == null) {
			return new List();
		}

		while (current != null) {
			if (current.getMessage().getAuthor() == user) {
				output.add(current.getMessage());
			}
			current = current.getNext();
		}
		return output;
	}

	/** Gibt eine String-Repräsentation dieser Liste zurück. Dabei werden die String-Repräsentationen der einzelnen
	 *  Messages mit Zeilenumbrüchen aneinandergehängt.
	 * @return Die String-Repräsentation dieser Liste.
	 */
	public String toString() {
		// TODO: Implementiere diese Methode
		String output = "";
		ListElement current = head;
		int i = 0;

		while (current != null) {
			output = output + current.getMessage().toString() + "\n";
			current = current.getNext();
		}
		return output;
	}

	public static List merge(List l1, List l2) {
		List merged = new List();
		ListElement c1 = l1.head;
		ListElement c2 = l2.head;

		while (c1 != null || c2 != null) {
			if (c1 == null) {
				merged.add(c2.getMessage());
				c2 = c2.getNext();
				continue;
			}
			if (c2 == null) {
				merged.add(c1.getMessage());
				c1 = c1.getNext();
				continue;
			}
			if (c1.getMessage().getTimestamp().isBefore(c2.getMessage().getTimestamp()) ||
					c1.getMessage().getTimestamp().isEqual(c2.getMessage().getTimestamp())) {
				merged.add(c1.getMessage());
				c1 = c1.getNext();
			} else {
				merged.add(c2.getMessage());
				c2 = c2.getNext();
			}
		}
		return merged;
	}
}
