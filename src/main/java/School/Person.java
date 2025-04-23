package School;

public class Person {
    private String name; // Setting to Private so they can only be accessed inside the class
    private int age;
    private String email;

    public Person(String name, int age, String email) {
        setName(name);
        setAge(age);
        setEmail(email);
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if(age < 0) { // If we are dividing our school from pre-school, elementary, or highschool we could adjust
            // the age variable higher than 0 so we can make it that highschool must be older than 13
            throw new IllegalArgumentException("Age must be more than negative.");
        }
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty.");
        }
        this.email = email;
    }
}
