package com.w6.data.dao.event;

import com.w6.data.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event findById(long id) {
        return eventRepository.findById(id);
    }

    @Override
    public List<Event> findAll() {
        List<Event> result = new ArrayList<>();
        eventRepository.findAll().forEach(result::add);

        return result;
    }

    @Override
    public List<Event> findByDateStartingWith(String datePrefix) {
        return eventRepository.findByDateStartingWith(datePrefix);
    }


    @Override
    public Event save(Event event) {
        if (event.getId() == -1) {
            long totalCount = eventRepository.count();
            event.setId(totalCount + 1);
        }
        return eventRepository.save(event);
    }

    @Override
    public long count() {
        return eventRepository.count();
    }
}
