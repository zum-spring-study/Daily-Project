package com.week.zumgnmarket.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Table(name = "musical_ticket")
public class MusicalTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_idx")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "musicla_idx")
    private Musical musical;

    @Column(name = "ticket_count")
    private Long ticketCount;

    @Column(name = "ticketing_date")
    private LocalDate ticketingDate;

    public void decrease() {
        if((this.ticketCount - 1) < 0 ){
            throw new RuntimeException("오늘자 티켓팅이 마감되었습니다 (재고 부족)");
        }
        this.ticketCount -= ticketCount;
    }

    public boolean checkTicketingDate(LocalDate localDate) {
        return this.ticketingDate.equals(localDate);
    }
}
