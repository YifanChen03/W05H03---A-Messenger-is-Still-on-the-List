package pgdp.messenger;

public class PinguTalk {
    // TODO: Implementiere die fehlenden Attribute
    private static long topicID;
    private static long userID;

    private UserArray members;
    private Topic[] topics;

    // TODO: Implementiere den fehlenden Konstruktor
    public PinguTalk(int initCapacity, int topicLength) {
        if (initCapacity > 0) {
            members = new UserArray(initCapacity);
        } else {
            members = new UserArray(1);
        }

        if (topicLength > 0) {
            topics = new Topic[topicLength];
        } else {
            topics = new Topic[1];
        }
    }

    // TODO: Implementiere die fehlenden Methoden
    public User addMember(String name, User supervisor) {
        User r_user = new User(userID, name, supervisor);
        members.addUser(r_user);
        userID++;
        return r_user;
    }

    public User deleteMember(long id) {
        return members.deleteUser(id);
    }

    public Topic createNewTopic(String name) {
        Topic n_topic = new Topic(topicID, name);

        if (topics != null) {
            for (int i = 0; i < topics.length; i++) {
                if (topics[i] == null) {
                    topics[i] = n_topic;
                    topicID++;
                    return n_topic;
                }
            }
            //Array ist voll
            return null;
        }

        return n_topic;
    }

    public Topic deleteTopic(long id) {
        if (topics != null) {
            for (int i = 0; i < topics.length; i++) {
                if (topics[i].getId() == id) {
                    Topic t_topic = topics[i];
                    topics[i] = null;
                    return t_topic;
                }
            }
        }
        return null;
    }

    //Getter und Setter

    public static long getTopicID() {
        return topicID;
    }

    public static void setTopicID(long topicID) {
        PinguTalk.topicID = topicID;
    }

    public static long getUserID() {
        return userID;
    }

    public static void setUserID(long userID) {
        PinguTalk.userID = userID;
    }
}
