package com.thebug.library_system.data;

import com.thebug.library_system.model.AppUser;

import java.util.Collection;

public interface AppUserDAO {

    AppUser findById(int id);
    Collection<AppUser> findAll();
    AppUser create(AppUser appUser);
    AppUser update(AppUser appUser);
    void delete(int id);
}
