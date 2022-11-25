package pgdp.messenger;

public class UserArray {
	// TODO: Implementiere die fehlenden Attribute
	private int initCapacity;
	private User[] users;

	public UserArray(int initCapacity) {
		this.initCapacity = initCapacity;

		if (initCapacity > 0) {
			users = new User[initCapacity];
		} else {
			users = new User[1];
		}
	}

	/** Fügt den übergebenen Nutzer in das durch dieses Objekt dargestellte 'UserArray' ein
	 * @param user Eine beliebige User-Referenz (schließt 'null' mit ein)
	 */
	public void addUser(User user) {
		//prüfen ob user schon vorhanden ist?
		// TODO: Implementiere diese Methode!
		if (user != null) {
			for (int i = 0; i < users.length; i++) {
				if (users[i] == null) {
					users[i] = user;
					break;
				}
			}

			//Array ist voll
			User[] n_users = new User[users.length * 2];
			for (int i = 0; i < users.length; i++) {
				if (users[i] == null) {
					users[i] = user;
					break;
				}
			}
		}
	}

	/** Entfernt den Nutzer mit der übergebenen ID aus dem Array und gibt diesen zurück.
	 *  Wenn kein solcher Nutzer existiert, wird 'null' zurückgegeben.
	 * @param id Ein beliebiger long
	 * @return Der eben aus dem UserArray entfernte Nutzer, wenn ein Nutzer mit der übergebenen id darin existiert, sonst 'null'
	 */
	public User deleteUser(long id) {
		// TODO: Implementiere diese Methode!
		for (int i = 0; i < users.length; i++) {
			if (users[i].getId() == id) {
				User t_user = users[i];
				users[i] = null;
				return t_user;
			}
		}
		//falls kein Nutzer mit passender ID existiert
		return null;
	}

	// TODO: Implementiere die fehlenden Methoden!
	public int size() {
		/*int c = 0;
		//zähle Nutzer in users[]
		for (int i = 0; i < users.length; i++) {
			if (users[i] != null) {
				c++;
			}
		}
		return c;*/
		return users.length;
	}

	//Getter und Setter

	public int getInitCapacity() {
		return initCapacity;
	}

	public void setInitCapacity(int initCapacity) {
		this.initCapacity = initCapacity;
	}

	public User[] getUsers() {
		return users;
	}

	public void setUsers(User[] users) {
		this.users = users;
	}
}
