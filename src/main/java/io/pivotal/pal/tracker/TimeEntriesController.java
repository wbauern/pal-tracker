package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by wbauer on 7/18/17.
 */
@RestController
public class TimeEntriesController {
    private TimeEntryRepository repo;

    public TimeEntriesController(TimeEntryRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/timeEntries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable("id") Long id) {
        TimeEntry entry = repo.get(id);
        if (entry != null) {
            return new ResponseEntity<>(entry, HttpStatus.OK);
        } else {
            return new ResponseEntity<TimeEntry>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/timeEntries")
    public ResponseEntity<List<TimeEntry>> list() {
        return new ResponseEntity<>(repo.list(), HttpStatus.OK);
    }

    @PostMapping("/timeEntries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry entry) {
        TimeEntry createdEntry = repo.create(entry);
        return new ResponseEntity<TimeEntry>(createdEntry, HttpStatus.CREATED);
    }

//    @PutMapping("/timeEntries/{id}")
//    public ResponseEntity<TimeEntry> update(@PathVariable("id") Long id, @RequestBody TimeEntry newEntry) {
//        TimeEntry entry = repo.get(id);
//        if (entry != null) {
//            return new ResponseEntity<>(entry, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<TimeEntry>(HttpStatus.NOT_FOUND);
//        }
//    }
    
    @PutMapping("/timeEntries/{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable Long id, @RequestBody TimeEntry timeEntry) {
        TimeEntry updatedTimeEntry = repo.update(id, timeEntry);
        if (updatedTimeEntry != null) {
            return new ResponseEntity<>(updatedTimeEntry, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/timeEntries/{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable Long id) {
        repo.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
