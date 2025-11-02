/**
 *
 */

package it.unibo.collections.social.impl;

import it.unibo.collections.social.api.SocialNetworkUser;
import it.unibo.collections.social.api.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This will be an implementation of
 * {@link SocialNetworkUser}:
 * 1) complete the definition of the methods by following the suggestions
 * included in the comments below.
 *
 * @param <U>
 *            Specific {@link User} type
 */
public final class SocialNetworkUserImpl<U extends User> extends UserImpl implements SocialNetworkUser<U> {

    /*
     *
     * [FIELDS]
     *
     * Define any necessary field
     *
     * In order to save the people followed by a user organized in groups, adopt
     * a generic-type Map:
     *
     * think of what type of keys and values would best suit the requirements
     */
    //Mappa che associa a ogni stringa (che rappresenta il gruppo) ad un Set (che rappresenta le persone che appartengono a questo grupppo)
    private final Map<String, Set<U>> followedUsers = new HashMap<>();
    /*
     * [CONSTRUCTORS]
     *
     * 1) Complete the definition of the constructor below, for building a user
     * participating in a social network, with 4 parameters, initializing:
     *
     * - firstName
     * - lastName
     * - username
     * - age and every other necessary field
     */

    /**
     * Builds a user participating in a social network.
     *
     * @param name
     *            the user firstname
     * @param surname
     *            the user lastname
     * @param userAge
     *            user's age
     * @param user
     *            alias of the user, i.e. the way a user is identified on an
     *            application
     */
    public SocialNetworkUserImpl(final String name, final String surname, final String user, final int userAge) {
        super(name, surname, user, userAge);
    }

    /*
     * 2) Define a further constructor where the age defaults to -1
     */
    public SocialNetworkUserImpl(final String name, final String surname, final String user) {
        super(name, surname, user, -1);
    }

    /*
     * [METHODS]
     *
     * Implements the methods below
     */
    @Override
    public boolean addFollowedUser(final String circle, final U user) {
        
        if (this.followedUsers.get(circle) != null){
            return this.followedUsers.get(circle).add(user);
        }else{
            final Set<U> follower = new HashSet<>();
            follower.add(user);
            this.followedUsers.put(circle,follower);
            return true;
        }
    }

    /**
     *
     * [NOTE] If no group with groupName exists yet, this implementation must
     * return an empty Collection.
     */
    @Override
    public Collection<U> getFollowedUsersInGroup(final String groupName) {
        if(!this.followedUsers.containsKey(groupName)){
            return Collections.emptySet(); 
        }else{
            /* 
            Collection<U> followedUsersInGroup= this.followedUsers.get(groupName);
            cosi creerei una nuova etichetta che punta allo stesso set
            SAREBBE QUINDI MODIFICABILE
            */
            return new HashSet<>(this.followedUsers.get(groupName));
        }
    }

    @Override
    public List<U> getFollowedUsers() {
        final Set<U> allUsers = new HashSet<>();
        for (final Set<U> groupSet : this.followedUsers.values()) {
            allUsers.addAll(groupSet);
        }
        return new ArrayList<>(allUsers);
    }
}
