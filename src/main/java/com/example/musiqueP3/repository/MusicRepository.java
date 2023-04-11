package com.example.musiqueP3.repository;

import com.example.musiqueP3.entities.Music;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Repository
public class MusicRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public List<Music> list() {
        return jdbcTemplate.query("SELECT MUSIC_ID, TITLE, DESCRIPTION FROM MUSIC",
                (rs, rowNum) -> new Music(
                        String.valueOf(rs.getLong("MUSIC_ID")),
                        rs.getString("TITLE"),
                        rs.getString("DESCRIPTION")
        ));
    }

    public void add(@NonNull Music music) {
        jdbcTemplate.update("INSERT INTO MUSIC (title, description) VALUES (?, ?)",
                music.getTitle(), music.getDescription());
    };

    public Music view(String number) {
        return jdbcTemplate.queryForObject("SELECT MUSIC_ID, TITLE, DESCRIPTION FROM MUSIC WHERE MUSIC_ID = ?",
                new Object[] {number},
                        (rs, rowNum) -> new Music(
                        String.valueOf(rs.getLong("MUSIC_ID")),
                        rs.getString("TITLE"),
                        rs.getString("DESCRIPTION")
                ));
    }

    public void update(Music music) {
        jdbcTemplate.update("UPDATE MUSIC SET TITLE = ?, DESCRIPTION = ? where MUSIC_ID = ?",
                music.getTitle(),
                music.getDescription(),
                music.getMusic_id()
        );
    };
}
