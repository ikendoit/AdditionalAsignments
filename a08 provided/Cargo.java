import java.util.*;
import java.util.stream.*;

/** A cargo module for holding Items. Items can be retrieved in various ways.
    @author Jeremy Hilliker @ Langara
    @author Trung Kien Nguyen  -  ID: 100284963
    @Acknowledgement StackOverFlow + javadoc : methods and stream support.
    @version 2017-05-16 15h00
    @see <a href="https://d2l.langara.bc.ca/d2l/le/content/88736/viewContent/1313159/View">a 03: Cargo</a>
*/
public class Cargo {

    private final int maxWeight;
    private final Collection<Item> cargo;

    /** Create a new cargo module with a given weight capacity.
        @param aMaxWeight the maximum weight capacity of the cargo module before it becomes overweight.
    */
    public Cargo(int aMaxWeight) {
        maxWeight = aMaxWeight;
        cargo = new HashSet<>();
    }

//****************METHODS OF CARGO***************** */

    /** Gets the maximum weight capacity of the cargo module.
        @return the maximum weight capacity of the cargo module.
    */
    public int getMaxWeight() {
        return maxWeight;
    }

    /** Gets the number of items currently stored.
        @return the number of items currently stored
    */
    public int getItemCount() {
        return cargo.size();
    }

    /** Get the sum of the weights of all of the items currently stored.
        @return the sum of the weights of all of the items currently stored
    */
    public int getTotalWeight() {
        return cargo.stream()
                    .mapToInt(item -> item.getWeight())
                    .sum();
    }

    /** Determines if the total weight of all of the stored items exceeds the weight capacity of the cargo module.
        @return true iff the total weight of all of the stored items exceeds the weight capacity of the cargo module.
    */
    public boolean isOverWeight() {
        return getTotalWeight() > maxWeight;
    }

    /** Adds an item to the cargo module.
        @param item the item to add. It must not already be in the cargo module.
    */
    public void add(Item item) {
        cargo.add(item);
    }

    /** Determines if an item is stored in the cargo module.
        @param tracking the tracking number of the item to search for
        @return true iff an item with the given tracking umber is stored in the cargo module
    */
    public boolean contains(long tracking) {
        return cargo.stream()
                    .mapToLong(item -> item.getTracking())
                    .anyMatch(track -> track == tracking);
    }

    /** Removes an item from the cargo hold.
        @param tracking the tracking number of the item to remove from the cargo module
        @return true iff an item was removed
    */
    public boolean remove(long tracking) {
        if (contains(tracking)){
            cargo.removeAll(getItems(tracking));
            return true;
        } else{
            return false;
        }
    }

//****************GET WEIGHT + AVERAGE ****************** */
    /** Method made by student 
     * Gets the heaviest item in the specified collection
     * @param collection the collection to find heaviest item
     * @return item      the heaviest item in the specified set
     */
    public Item getHeaviest(Collection<Item> items){
        if (items == null || items.size() ==0 ){
            return null;
        }
        return items.stream()
            .sorted(Comparator.comparing(Item::getWeight).reversed())
            .findFirst()
            .get();
    }

    /** Gets the heaviest item in the cargo module.
        @return the heaviest item in the cargo module; null if empty
    */
    public Item getHeaviest() {
        if (cargo == null || cargo.size() == 0) {
            return null;
        }
        return getHeaviest(cargo);
    }

    /** Gets the heaviest item with the given name.
        @param name the name with which to filter
        @return the heaviest item with the given name; null if no item matches the name
    */
    public Item getHeaviest(String name) {
        if (name == null ){
            return null;
        }
        return getHeaviest(getItems(name));
    }

    /** Gets the average weight of the items in the cargo module.
        @return the average weight of the items in the cargo module; NaN if empty
    */
    public double getAverageWeight() {
        if (cargo.size() != 0 || cargo ==null){
            return 1.0*getTotalWeight()/cargo.size();
        }
        return Double.NaN;
    }

    /** Gets the average weight of the items with the given name.
        @param name the name with which to filter
        @return the average weight of the items with the given name; NaN if no item matches the given name
    */
    public double getAverageWeight(String name) {
        if (getItems(name).size() != 0 || getItems(name) == null){
            return getItems(name).stream()
                                 .mapToInt(item -> item.getWeight())
                                 .sum() * 1.0 / getItems(name).size(); 
        }
		return Double.NaN;
    }

//****************GET ITEMS ************************** */

    /** Retrieves the items with the given tracking numbers.
        @param tracking the tracking numbers of the items to retrieve
        @return the items with the given tracking numbers
    */
    public Set<Item> getItems(long... tracking) {
        if (tracking == null ){
            return null;
        }
        Arrays.sort(tracking);
		return cargo.stream()
                    .filter(item -> Arrays.binarySearch(tracking, item.getTracking()) >= 0)
                    .collect(Collectors.toSet());
    }

    /** Gets all items with the given name.
        @param name the name to filter items by
        @return the set of all items with the given name.
    */
    public Set<Item> getItems(String name) {
        if (name == null || name ==""){
            return new HashSet<Item>();
        }
        return cargo.stream()
                    .filter(item -> item.getName() == name) 
                    .collect(Collectors.toSet());
    }

    /** Retrieves the items with the given names.
        @param names the names of the items to retrieve
        @return a map from names to sets of items with that name.
    */
    public Map<String, List<Item>> getItemsMap(String... names) {
        if (names == null){
            return null;
        }
        Set<String> namesList = new HashSet<String>(Arrays.asList(names));
		return namesList.stream()
                        .collect(Collectors.toMap(name -> name, name -> Collections.list(Collections.enumeration(getItems(name)))));
    }

//****************CLASS: Item*****************
    /** An item in the cargo module. Items are immutable.
    */
    public static class Item {
        private final long tracking;
        private final String name;
        private final int weight;

        private static long nextTrack = 101;

        /** An item for transport.
            @param aName the name of the item
            @param aWeight the weight of the item
            */
        public Item(String aName, int aWeight) {
            tracking = nextTrack++;
            name = aName;
            weight = aWeight;
        }

        public long getTracking() {
            return tracking;
        }
        public int getWeight() {
            return weight;
        }
        public String getName() {
            return name;
        }
    }
}
