package com.example.cafe.entity;

import com.example.cafe.entity.listener.LibraryEntityListener;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

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
