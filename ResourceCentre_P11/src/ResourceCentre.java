import java.util.ArrayList;

public class ResourceCentre {

	private static final int ITEM_TYPE_CHROMEBOOK = 2;
	private static final int ITEM_TYPE_CAMCORDER = 1;
	private static final int OPTION_RETURN = 4;
	private static final int OPTION_LOAN = 3;
	private static final int OPTION_ADD = 2;
	private static final int OPTION_VIEW = 1;
	private static final int OPTION_QUIT = 5;

	public static void main(String[] args) {

		ArrayList<Camcorder> camcorderList = new ArrayList<Camcorder>();
		ArrayList<Chromebook> chromebookList = new ArrayList<Chromebook>();

		camcorderList.add(new Camcorder("CC001", "Sony HDR-CX405", 35));
		camcorderList.add(new Camcorder("CC002", "Panasonic HC-MDH2", 10));
		chromebookList.add(new Chromebook("CB001", "ASUS Chromebook ", "Win 10"));
		chromebookList.add(new Chromebook("CB002", "HP Chromebook", "Win 10"));
		
		//Alyssa
		int option = 0;
		
		//Alyssa - Fixed bracket issues, not able to run option 2-4 earlier on
		while (option != OPTION_QUIT) {
			//1233
			ResourceCentre.menu();
			option = Helper.readInt("Enter an option > ");


			if (option == OPTION_VIEW) {

				final boolean Display = option == 1;
				
			if (Display) {

				// View all items
				ResourceCentre.viewAllCamcorder(camcorderList);
				ResourceCentre.viewAllChromebook(chromebookList);
			}

			} else if (option == OPTION_ADD) {
				// Add a new item
				ResourceCentre.setHeader("ADD");
				//jiawei
				itemTypeMenu();
				
				int itemType = Helper.readInt("Enter option to select item type > ");

				if (itemType == ITEM_TYPE_CAMCORDER) {
					// Add a camcorder
					Camcorder cc = inputCamcorder();
					ResourceCentre.addCamcorder(camcorderList, cc);

				} else if (itemType == ITEM_TYPE_CHROMEBOOK) {
					// Add Chromebook
					Chromebook cb = inputChromebook();
					ResourceCentre.addChromebook(chromebookList, cb);

				} else {
					System.out.println("Invalid type");
				}

			} else if (option == OPTION_LOAN) {
				// Loan item
				ResourceCentre.setHeader("LOAN");
				//jiawei
				itemTypeMenu();
				
				int itemType = Helper.readInt("Enter option to select item type > ");

				if (itemType == 1) {
					// Loan camcorder
					ResourceCentre.loanCamcorder(camcorderList);
				} else if (itemType == 2) {
					// Loan Chromebook
					ResourceCentre.loanChromebook(chromebookList);
				} else {
					System.out.println("Invalid type");
				}

			} else if (option == OPTION_RETURN) {
				// Return item
				ResourceCentre.setHeader("RETURN");	
				//jiawei
				itemTypeMenu();
				
				int itemType = Helper.readInt("Enter option to select item type > ");
				if (itemType == 1) {
					// Return camcorder
					ResourceCentre.returnCamcorder(camcorderList);
				} else if (itemType == 2) {
					// Return Chromebook
					ResourceCentre.returnChromebook(chromebookList);
				} else {
					System.out.println("Invalid type");
				}

			} else if (option == 5) {
				System.out.println("Bye!");
			} else {
				System.out.println("Invalid option");
			}

		}}

	private static void itemTypeMenu() {
		ResourceCentre.setHeader("ITEM TYPES");
		System.out.println("1. Camcorder");
		System.out.println("2. Chromebook");
	}

	public static void menu() {
		ResourceCentre.setHeader("RESOURCE CENTRE APP");
		System.out.println("1. Display Inventory");
		System.out.println("2. Add item");
		System.out.println("3. Loan item");
		System.out.println("4. Return item");
		System.out.println("5. Quit");
		Helper.line(80, "-");

	}

	public static void setHeader(String header) {
		Helper.line(80, "-");
		System.out.println(header);
		Helper.line(80, "-");
	}

	public static String showAvailability(boolean isAvailable) {
		String avail;

		if (isAvailable == true) {
			avail = "Yes";
		} else {
			avail = "No";
		}
		return avail;
	}

	// ================================= Option 1 View
	// =================================
	public static String retrieveAllCamcorder(ArrayList<Camcorder> camcorderList) {
		String output = "";
		

		for (int i = 0; i < camcorderList.size(); i++) {

			//Jonathan 
			String assetTag = camcorderList.get(i).getAssetTag();
			String description = camcorderList.get(i).getDescription();
			String showAvailability = ResourceCentre.showAvailability(camcorderList.get(i).getIsAvailable());
			String dueDate = camcorderList.get(i).getDueDate();
			int opticalZoom = camcorderList.get(i).getOpticalZoom();
			output += String.format("%-10s %-30s %-10s %-10s %-20d\n", assetTag,
				description, showAvailability,dueDate,opticalZoom);

		}
		return output;
	}

	public static void viewAllCamcorder(ArrayList<Camcorder> camcorderList) {
		ResourceCentre.setHeader("CAMCORDER LIST");
		//jiawei
		String output = format_for_Camcorder();
		output += retrieveAllCamcorder(camcorderList);
		System.out.println(output);
	}
	//jiawei
	private static String format_for_Camcorder() {
		return String.format("%-10s %-30s %-10s %-10s %-20s\n", "ASSET TAG", "DESCRIPTION", "AVAILABLE",
				"DUE DATE", "OPTICAL ZOOM");
	}

	public static String retrieveAllChromebook(ArrayList<Chromebook> chromebookList) {
		String output = "";
		// write your code here
		for (int i = 0; i < chromebookList.size(); i++) {
			
			//Jonathan
			String assetTag = chromebookList.get(i).getAssetTag();
			String description = chromebookList.get(i).getDescription();
			String showAvailability = ResourceCentre.showAvailability(chromebookList.get(i).getIsAvailable());
			String dueDate = chromebookList.get(i).getDueDate();
			String os = chromebookList.get(i).getOs();
			output += String.format("%-10s %-30s %-10s %-10s %-20s\n", assetTag,description, showAvailability,dueDate,os);
		}
		return output;
	}

	public static void viewAllChromebook(ArrayList<Chromebook> chromebookList) {

		ResourceCentre.setHeader("CHROMEBOOK LIST");
		//jiawei
		String output = format_for_Chromebook();
		output += retrieveAllChromebook(chromebookList);
		System.out.println(output);
	}
	//jiawei
	private static String format_for_Chromebook() {
		return String.format("%-10s %-30s %-10s %-10s %-20s\n", "ASSET TAG", "DESCRIPTION", "AVAILABLE",
				"DUE DATE", "OPERATING SYSTEM");
	}

	// ================================= Option 2 Add =================================
	public static Camcorder inputCamcorder() {
		//jiawei
		String tag = ask_for_assetTag();
		String description = ask_for_description();
		int zoom = Helper.readInt("Enter optical zoom > ");

		Camcorder cc = new Camcorder(tag, description, zoom);
		return cc;

	}
	//jiawei
	private static String ask_for_assetTag() {
		String tag = Helper.readString("Enter asset tag > ");
		return tag;
	}
	//jiawei
	private static String ask_for_description() {
		String description = Helper.readString("Enter description > ");
		return description;
	}

	public static void addCamcorder(ArrayList<Camcorder> camcorderList, Camcorder cc) {

		camcorderList.add(cc);
		System.out.println("Camcorder added");
	}

	public static Chromebook inputChromebook() {
		//jiawei
		String tag = ask_for_assetTag();
		//jiawei
		String description = ask_for_description();
		String os = Helper.readString("Enter operating system > ");

		Chromebook cb = new Chromebook(tag, description, os);
		return cb;

	}

	public static void addChromebook(ArrayList<Chromebook> chromebookList, Chromebook cb) {

		chromebookList.add(cb);
		System.out.println("Chromebook added");
	}

	// ================================= Option 3 Loan =================================
	public static boolean doLoanCamcorder(ArrayList<Camcorder> camcorderList, String tag, String dueDate) {

		boolean isLoaned = false;

		for (int i = 0; i < camcorderList.size(); i++) {

			String assetTag = camcorderList.get(i).getAssetTag();
			// Daven
			final boolean isAvailable2 = camcorderList.get(i).getIsAvailable();
			boolean isAvailable = isAvailable2;
			if (tag.equalsIgnoreCase(assetTag) && isAvailable == true) {

				camcorderList.get(i).setIsAvailable(false);
				camcorderList.get(i).setDueDate(dueDate);

				isLoaned = true;

			}
		}
		return isLoaned;
	}

	public static void loanCamcorder(ArrayList<Camcorder> camcorderList) {
		ResourceCentre.viewAllCamcorder(camcorderList);
		//jiawei
		String tag = ask_for_AssetTag();
		String due = ask_for_dueDate();
		Boolean isLoaned = doLoanCamcorder(camcorderList, tag, due);
		if (isLoaned == false) {
			System.out.println("Invalid asset tag");
		} else {
			System.out.println("Camcorder " + tag + " loaned out");
		}
	}
	//jiawei
	private static String ask_for_dueDate() {
		String due = Helper.readString("Enter due date > ");
		return due;
	}
	//jiawei
	private static String ask_for_AssetTag() {
		String tag = ask_for_assetTag();
		return tag;
	}

	public static boolean doLoanChromebook(ArrayList<Chromebook> chromebookList, String tag, String dueDate) {
		// write your code here
		boolean isLoaned = false;

		for (int i = 0; i < chromebookList.size(); i++) {
			// Daven
			final String assetTag = chromebookList.get(i).getAssetTag();
			final boolean isAvailable = chromebookList.get(i).getIsAvailable();
			if (tag.equalsIgnoreCase(assetTag) && isAvailable == true) {

				chromebookList.get(i).setIsAvailable(false);
				chromebookList.get(i).setDueDate(dueDate);

				isLoaned = true;

			}
		}
		return isLoaned;
	}

	public static void loanChromebook(ArrayList<Chromebook> chromebookList) {
		// write your code here
		ResourceCentre.viewAllChromebook(chromebookList);
		//jiawei
		String tag = ask_for_AssetTag();
		String due = ask_for_dueDate();
		//Joshua
		Boolean Loan = doLoanChromebook(chromebookList, tag, due);
		if (Loan == false) {
			System.out.println("Invalid asset tag");
		} else {
			System.out.println("Chromebook " + tag + " loaned out");
		}

	}

	// ================================= Option 4 Return
	// =================================
	public static boolean doReturnCamcorder(ArrayList<Camcorder> camcorderList, String tag) {
		boolean isReturned = false;

		for (int i = 0; i < camcorderList.size(); i++) {

			// Daven
			final String assetTag = camcorderList.get(i).getAssetTag();
			final boolean isAvailable = camcorderList.get(i).getIsAvailable();
			if (tag.equalsIgnoreCase(assetTag) && isAvailable == false) {

			//Daven
			
			if (tag.equalsIgnoreCase(assetTag)
					&& isAvailable == false) {

				camcorderList.get(i).setIsAvailable(true);
				camcorderList.get(i).setDueDate("");
				isReturned = true;

			}
		}
		}
		return isReturned;

	}

	public static void returnCamcorder(ArrayList<Camcorder> camcorderList) {
		//jiawei
		ResourceCentre.viewAllCamcorder(camcorderList);
		String tag = ask_for_AssetTag();
		Boolean isReturned = doReturnCamcorder(camcorderList, tag);

		if (isReturned == false) {
			System.out.println("Invalid asset tag");
		} else {
			System.out.println("Camcorder " + tag + " returned");
		}
	}

	// write your doReturnChromebook code here
	public static boolean doReturnChromebook(ArrayList<Chromebook> chromebookList, String tag) {
		boolean isReturned = false;

		for (int i = 0; i < chromebookList.size(); i++) {

			//Daven
			String assetTag = chromebookList.get(i).getAssetTag();
			boolean isAvailable = chromebookList.get(i).getIsAvailable();
			if (tag.equalsIgnoreCase(assetTag)
					&& isAvailable == false) {
				
				chromebookList.get(i).setIsAvailable(true);
				chromebookList.get(i).setDueDate("");
				isReturned = true;

			}
		}
		return isReturned;

	}

	public static void returnChromebook(ArrayList<Chromebook> chromebookList) {
		// write your code here
		ResourceCentre.viewAllChromebook(chromebookList);

		//jiawei
		String tag = ask_for_AssetTag();


		
		//Daven
	    boolean doReturnChromebook = doReturnChromebook(chromebookList, tag);
		Boolean isReturned = doReturnChromebook;
		

		if (isReturned == false) {
			System.out.println("Invalid asset tag");
		} else {
			System.out.println("Chromebook " + tag + " returned");
		}
	}

}
