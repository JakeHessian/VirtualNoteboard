package networks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * Note: note coordinates are bottom left corner of note (positive x,y quadrant)
 */
public class Server {
	private static ArrayList<Note> notes = new ArrayList<Note>();
	private static ArrayList<Pin> pins = new ArrayList<Pin>();
	public static int maxHeight;
	public static int maxWidth;

	public static ArrayList<String> colors = new ArrayList<String>();
	public static int portNumber = 0;

	public static void main(String[] args) throws IOException {
		System.out.println("\r\n"
				+ "  ____           _        _ _      _   _       _          ____                      _  \r\n"
				+ " |  _ \\ ___  ___| |_     (_) |_   | \\ | | ___ | |_ ___   | __ )  ___   __ _ _ __ __| | \r\n"
				+ " | |_) / _ \\/ __| __|____| | __|  |  \\| |/ _ \\| __/ _ \\  |  _ \\ / _ \\ / _` | '__/ _` | \r\n"
				+ " |  __/ (_) \\__ \\ ||_____| | |_   | |\\  | (_) | ||  __/  | |_) | (_) | (_| | | | (_| | \r\n"
				+ " |_|   \\___/|___/\\__|    |_|\\__|  |_| \\_|\\___/ \\__\\___|  |____/ \\___/ \\__,_|_|  \\__,_| \r\n"
				+ "  ____                                _                _ _           _   _             \r\n"
				+ " / ___|  ___ _ ____   _____ _ __     / \\   _ __  _ __ | (_) ___ __ _| |_(_) ___  _ __  \r\n"
				+ " \\___ \\ / _ \\ '__\\ \\ / / _ \\ '__|   / _ \\ | '_ \\| '_ \\| | |/ __/ _` | __| |/ _ \\| '_ \\ \r\n"
				+ "  ___) |  __/ |   \\ V /  __/ |     / ___ \\| |_) | |_) | | | (_| (_| | |_| | (_) | | | |\r\n"
				+ " |____/ \\___|_|    \\_/ \\___|_|    /_/   \\_\\ .__/| .__/|_|_|\\___\\__,_|\\__|_|\\___/|_| |_|\r\n"
				+ "                                          |_|   |_|                                    \r\n"
				+ "=======================================================================================\n "
				+ "Server coded by: Jake Hessian and Katie Milligan");
		final int PORT = Integer.parseInt(args[0]);

		Server.maxWidth = Integer.parseInt(args[1]);
		Server.maxHeight = Integer.parseInt(args[2]);
		System.out.printf("\nPort: %d, Max Width: %d, Max Height: %d\n", PORT, maxWidth, maxHeight);
		System.out.println("Colors availble for notes: ");
		for (int x = 3; x < args.length; x++) {
			System.out.print(args[x] + " ");
			colors.add(args[x]);

		}
		System.out.println();

		System.out.println("Server starting...");
		ServerSocket listener = new ServerSocket(PORT); // run on port 9898
		int clientNumber = 1;

		try {
			while (true) {
				new Noteboard(listener.accept(), clientNumber++).start();
			}
		} finally {
			listener.close();
		}

	}

	/*
	 * This function will clean all the notes that are not pinned.
	 */
	public static int clearAll(ArrayList<Note> notes) {
		int x;
		Note i;
		ArrayList<Note> l = new ArrayList<Note>();
		for (x = 0; x < notes.size(); x++) {
			if (!notes.get(x).isPinned()) {
				l.add(notes.get(x));
			}
		}
		for (x = 0; x < l.size(); x++) {
			i = l.get(x);
			notes.remove(i);
		}
		return l.size();
	}

	/*
	 * This function will unpin pins on the note board.
	 */
	public static void parseUnPin(String s, ArrayList<Note> notes, ArrayList<Pin> pins, PrintWriter out) {
		Scanner scanner = new Scanner(s);
		Note currentNote = null;
		int x = -1;
		int y = -1;
		int numberPins = 0;
		Pin z;
		// ArrayList<Note> notes = new ArrayList<Note>();
		ArrayList<Pin> l = new ArrayList<Pin>();
		if (scanner.hasNext()) {
			scanner.next();
		} else {
			out.println("Error UnPinning. x must be an int [0," + Server.maxWidth + "] and y must be [0,"
					+ Server.maxHeight + "] ");
			scanner.close();
			return;
		}

		if (scanner.hasNextInt()) {
			x = scanner.nextInt();
		}
		if (scanner.hasNextInt()) {
			y = scanner.nextInt();
		}
		if (x < 0 || y < 0 || y == -1 || x == -1) {
			out.println("Error UnPinning. x must be an int [0," + Server.maxWidth + "] and y must be [0,"
					+ Server.maxHeight + "] ");
			scanner.close();
			return;
		}
		int currXMax = 0;
		int currYMax = 0;
		int currYMin = 0;
		int currXMin = 0;

		// to maintain integrity of pins list must do things this way :(
		numberPins = pins.size();
		for (int i = 0; i < numberPins; i++) {
			if (pins.get(i).getX() == x && pins.get(i).getY() == y) {
				l.add(pins.get(i));
			}
		}

		if (!l.isEmpty()) {
			for (int i = 0; i < l.size(); i++) {
				z = l.get(i);
				pins.remove(z); // java treated l.get as an object instead of an int...

			}
			out.println("Deleted pin(s) at: " + x + " " + y);
			// check to see if there are still pins on that note
			// nested for loop.. go through all notes and check to see if any pins still on
			// that note. I debated whether or not to make the pins point to a note to avoid
			// the below...
			// however I would've needed a nested for loop for the pin constructor then - to
			// search what pin landed on.
			// saved myself from the problem of having pins pointing to more than one note,
			// as notes can overlap.

			for (int i = 0; i < notes.size(); i++) {
				currentNote = notes.get(i);
				// probably should've just made currXMax/currYMax just note attributes..
				currXMax = currentNote.dimensions.getWidth() + currentNote.coords.getX();
				currYMax = currentNote.dimensions.getHeight() + currentNote.coords.getY();

				currXMin = currentNote.coords.getX();
				currYMin = currentNote.coords.getY();
				currentNote.setPinned(false);
				for (int a = 0; a < pins.size(); a++) {
					x = pins.get(a).getX();
					y = pins.get(a).getY();
					if (x <= currXMax && x >= currXMin) {
						if (y <= currYMax && y >= currYMin) {
							currentNote.setPinned(true);
						}
					}
				}
			}
		} else {
			out.println("No such pin(s) at: " + x + " " + y);
		}

		scanner.close();
		return;
	}

	/*
	 * This function will pin the note boards
	 */
	public static void parsePin(String s, ArrayList<Note> notes, ArrayList<Pin> pins, PrintWriter out) {
		Scanner scanner = new Scanner(s);
		int x = -1, y = -1;
		Note currentNote = null;
		scanner.next();
		if (scanner.hasNextInt()) {
			x = scanner.nextInt();
		}
		if (scanner.hasNextInt()) {
			y = scanner.nextInt();
		}
		int currXMax = 0;
		int currYMax = 0;
		int currYMin = 0;
		int currXMin = 0;

		if (x < 0 || y < 0 || x > Server.maxWidth || y > Server.maxHeight) {
			scanner.close();
			out.println("Error creating PIN. Pin x must be an int [0," + Server.maxWidth + "] and y must be [0,"
					+ Server.maxHeight + "] ");
			return;
		}

		Pin pin = new Pin(x, y);
		pins.add(pin); // added to pin list
		for (int i = 0; i < notes.size(); i++) {

			currentNote = notes.get(i);

			currXMax = currentNote.dimensions.getWidth() + currentNote.coords.getX();
			currYMax = currentNote.dimensions.getHeight() + currentNote.coords.getY();

			currXMin = currentNote.coords.getX();
			currYMin = currentNote.coords.getY();

			if (x <= currXMax && x >= currXMin) {
				if (y <= currYMax && y >= currYMin) {
					if (!currentNote.pinned) {
						currentNote.setPinned(true);
					}
				}

			}
		}
		out.println("Created pin at: " + x + " " + y);
		scanner.close();
		return;
	}

	/*
	 * This function will parse a string from a GET command.
	 */
	public static void parseGet(String s, ArrayList<Note> ns, PrintWriter out) {
		Scanner scner = new Scanner(s);
		ArrayList<Note> notes = new ArrayList<Note>();
		for (int i = 0; i < ns.size(); i++) {
			notes.add(ns.get(i));
		}
		ArrayList<Note> foundNotes = new ArrayList<Note>();
		Scanner scner2 = new Scanner(s);
		// scner2.next();

		scner.next(); // scner past the GET portion of the string
		s = scner.next();
		if (s.startsWith("color=")) {
			String color = s.substring(6);

			for (int x = 0; x < notes.size(); x++) {
				if (notes.get(x).getColor().toString().equals(color)) {
					foundNotes.add(notes.get(x));
				}
			}
			if (scner.hasNext()) {
				s = scner.next();
				// System.out.println("Scnr.next() in color: "+scner.next());
			}

			notes.clear();
			for (int i = 0; i < foundNotes.size(); i++) {
				notes.add(foundNotes.get(i));
			}

			// System.out.println("Size of notes after color=: "+notes.size());
		}

		if (s.startsWith("contains=")) {
			foundNotes.clear();
			// System.out.println("Size of foundNotes after color: " + foundNotes.size());
			// System.out.println("Size of Notes after color: " + notes.size());
			Note currentNote = null;
			int x = scner.nextInt();
			int y = scner.nextInt();
			int currXMax = 0;
			int currYMax = 0;
			int currYMin = 0;
			int currXMin = 0;
			for (int i = 0; i < notes.size(); i++) {

				currentNote = notes.get(i);

				currXMax = currentNote.dimensions.getWidth() + currentNote.coords.getX();
				currYMax = currentNote.dimensions.getHeight() + currentNote.coords.getY();

				currXMin = currentNote.coords.getX();
				currYMin = currentNote.coords.getY();

				if (x <= currXMax && x >= currXMin) {
					if (y <= currYMax && y >= currYMin) {

						foundNotes.add(notes.get(i));
					}

				}
			}
			if (scner.hasNext()) {
				s = scner.next();
				// System.out.println("Scnr.next() in color: "+scner.next());
			}

			notes.clear();
			for (int i = 0; i < foundNotes.size(); i++) {
				notes.add(foundNotes.get(i));
			}

		}
		// System.out.println(s);
		if (s.startsWith("refersTo=")) {
			foundNotes.clear();
			String keyWord = s.substring(9).toUpperCase();
			for (int x = 0; x < notes.size(); x++) {
				if (notes.get(x).getText().toUpperCase().contains(keyWord)) {
					foundNotes.add(notes.get(x));
				}
			}

		}

		// send final list to client
		out.println(foundNotes.size());
		for (int x = 0; x < foundNotes.size(); x++) {
			out.println(foundNotes.get(x).toString());
		}

		// String input = scner.next().toString();

		scner.close();
		scner2.close();
		return;
	}

	/*
	 * This function will parse a string into a note, given the proper format.
	 * checks for proper input. Will return null if there is an error creating note
	 * 
	 * @return note or null
	 * 
	 */
	public static Note toNote(String s, PrintWriter out) {
		// Note n = null;
		Scanner scner = new Scanner(s);
		if (!scner.hasNext()) {
			// System.out.println("Error creating Note.");
			scner.close();
			return null;
		}
		scner.next(); // get past POST command prefix

		if (!scner.hasNextInt()) {
			// System.out.println("Error making note1");
			scner.close();
			return null;
		}
		int x = scner.nextInt();

		if (!scner.hasNextInt()) {
			// System.out.println("Error making note2");
			scner.close();
			return null;
		}
		int y = scner.nextInt();

		if (!scner.hasNextInt()) {
			// .out.println("Error making note3");
			scner.close();
			return null;
		}
		int height = scner.nextInt();
		if (!scner.hasNextInt()) {
			// System.out.println("Error making note4");
			scner.close();
			return null;
		}
		int width = scner.nextInt();

		if (!scner.hasNext()) {
			// System.out.println("Error making note5");
			scner.close();
			return null;
		}
		String stringColor = scner.next();
		if (!colors.contains(stringColor)) {
			out.println("Error creating Note. Choose from: " + colors);
			// System.out.println("Error making note6");
			scner.close();
			return null;
		}

		if (!scner.hasNextLine()) {
			System.out.println("Error making note7");
			scner.close();
			return null;
		}
		String text = scner.nextLine();

		scner.close();
		// Color color = Server.toColor(stringColor);

		/*
		 * error handling... if x or y exceed board - return null if height width exceed
		 * board - shrink to fit
		 */
		if (height < 0) {
			out.println("Error creating Note. Height must be greater than 0");
			return null;
		}
		if (width < 0) {
			out.println("Error creating Note. Width must be greater than 0");
			return null;
		}

		if (x > Server.maxWidth || x < 0) {
			out.println("Error creating Note. x must be [0," + Server.maxWidth + "]");
			return null;
		}
		if (y > Server.maxHeight || y < 0) {
			out.println("Error creating Note. y must be [0," + Server.maxHeight + "]");
			return null;
		}
		if (x + width > Server.maxWidth) {
			width = (x + width) - Server.maxWidth;
		}
		if (y + height > Server.maxHeight) {
			height = (y + height) - Server.maxHeight;
		}
		Note n = new Note(x, y, height, width, stringColor, text);

		return n;
	}

	private static class Noteboard extends Thread {

		private Socket socket;
		private int clientNumber;

		public Noteboard(Socket socket, int clientNumber) {
			this.socket = socket;
			this.clientNumber = clientNumber;
			log("New connection with client #" + clientNumber + " at " + socket);
		}

		public void run() {

			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				out.println("Hello, you are client #" + clientNumber + ".");
				String input = in.readLine();
				while (true && input != null) {
					if ((!input.isEmpty())) {
						String[] inArray = input.split("\\s");
						System.out.println("Client #" + clientNumber + ": " + input);
						if (input.equals("q")) {
							break;
						} else if (input.equals("GET ")) {
							out.println(notes.size()); // send the size to the client for display purposes
							for (int x = 0; x < notes.size(); x++) {
								out.println(notes.get(x).toString());
							}
						} else if (inArray[0].equals("GET") && !input.contains("PINS")) {
							// System.out.println("Getting specific");
							Server.parseGet(input, notes, out);
						} else if (inArray[0].equals("PIN")) {
							Server.parsePin(input, notes, pins, out);
						} else if (inArray[0].equals("UNPIN")) {
							Server.parseUnPin(input, notes, pins, out);
						} else if (input.contains("GET PINS")) {
							out.println(pins.size());
							for (int x = 0; x < pins.size(); x++) {
								out.println(pins.get(x));
							}
						} else if (input.equals("PONG")) {
							// System.out.println("clent typed PONG");
							out.println("ping");
						} else if (inArray[0].equals("POST")) {
							Note n = Server.toNote(input, out);
							if (n != null) {
								notes.add(n);
								out.println("Successfully registered note to board.");
							}
						} else if (input.equals("CLEAR ")) {
							out.println("Deleted " + Server.clearAll(notes) + " unpinned notes");
						} else if (input.equals("shutdown")) {
							out.println("Shutting down server... Goodbye");
							this.socket.close();
							System.exit(0);
						} else if (input.equals("DISCONNECT")) {
							out.println("Disconnecting Client");
							this.socket.close();
						} else {
							out.println(input + " is an invalid command.");
						}

					}
					input = null;
					input = in.readLine();

				}
			} catch (IOException e) {
				log("Error handling client #" + clientNumber + ": " + e);
			} finally {
				try {
					socket.close();
				} catch (IOException e) {
					log("Couldn't close a socket, what's going on?");
				}
				log("Connection with client #" + clientNumber + " closed");
			}
		}

		private void log(String message) {
			System.out.println(message);
		}
	}

	private static class Dimensions {
		private int height = -1;
		private int width = -1;

		public Dimensions(int height, int width) {
			this.height = height;
			this.width = width;
		}

		/**
		 * @return the width
		 */
		public int getWidth() {
			return width;
		}

		/**
		 * @return the height
		 */
		public int getHeight() {
			return height;
		}/**
			 * @param height the height to set
			 */
	}

	private static class Coordinates {
		private int x = -1;
		private int y = -1;

		public Coordinates(int x, int y) {
			this.x = x;
			this.y = y;

		}

		/**
		 * @return the x
		 */
		public int getX() {
			return x;
		}

		/**
		 * @return the y
		 */
		public int getY() {
			return y;
		}

	}

	private static class Pin {
		private int x = -1;
		private int y = -1;

		public Pin(int x, int y) {
			this.setX(x);
			this.setY(y);
		}

		public String toString() {
			String s = null;
			s = "Pin, x: " + this.x + " y: " + this.y;
			return s;
		}

		/**
		 * @return the x
		 */
		public int getX() {
			return x;
		}

		/**
		 * @param x the x to set
		 */
		public void setX(int x) {
			this.x = x;
		}

		/**
		 * @return the y
		 */
		public int getY() {
			return y;
		}

		/**
		 * @param y the y to set
		 */
		public void setY(int y) {
			this.y = y;
		}
	}

	private static class Note {

		private Coordinates coords;
		private Dimensions dimensions;
		private String text = null;
		private boolean pinned = false;
		private String color; // color is red by default

		public Note(int x, int y, int height, int width, String stringColor, String text) {

			this.coords = new Coordinates(x, y);
			this.dimensions = new Dimensions(width, width);
			this.text = text;
			this.color = stringColor;

		}

		public String toString() {
			String s = null;
			s = "x: " + this.coords.getX() + ", y: " + this.coords.getY() + ", Height: " + this.dimensions.getHeight()
					+ ", Width: " + this.dimensions.getWidth() + ", Text: " + this.text + ", Pinned: " + this.pinned
					+ ", Color: " + this.color;

			return s;
		}

		/**
		 * @return the pinned
		 */
		public boolean isPinned() {
			return pinned;
		}

		/**
		 * @param pinned the pinned to set
		 */
		public void setPinned(boolean pinned) {
			this.pinned = pinned;
		}

		/**
		 * @return the note
		 */
		public String getText() {
			return text;
		}

		/**
		 * @return the color
		 */
		public String getColor() {
			return color;
		}

	}
}
