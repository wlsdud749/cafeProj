package com.example.cafe.entity;

import com.example.cafe.entity.listener.LibraryEntityListener;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(value = { LibraryEntityListener.class })
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "cafe")
public class Cafe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동증가
    private Long idx;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "user_idx")
    private User user;

    @ToString.Exclude
    @OneToMany(mappedBy = "cafe")
    private final List<Room> rooms = new ArrayList<>();

//    @Column(insertable = false, updatable = false)
//    private Long user_idx;

    private String name;
    private String number;
    private String addr;
    private String addr2;
    private String x;
    private String y;
    private int table_cnt;

//    public void setUserIdx(Long userIdx) {
//        this.user_idx=userIdx;
//    }


//    private LocalDateTime open_form;
//    private LocalDateTime open_to;

}
