package com.w6.data.dao.event;

import com.w6.data.Event;

import java.util.List;

public interface EventService {

    Event findById(long id);

    List<Event> findAll();

    List<Event> findByDateStartingWith(String datePrefix);

    Event save(Event event);

    long count();

}
