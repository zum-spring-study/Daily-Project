package com.week.zumgnmarket.entity;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Slf4j
@Builder
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

    public static MusicalTicket of(Musical musical, Long ticketCount, LocalDate ticketingDate) {
        return MusicalTicket.builder()
                .musical(musical)
                .ticketCount(ticketCount)
                .ticketingDate(ticketingDate)
                .build();
    }

    public void decrease() {
        final String thread = Thread.currentThread().getName();
        if((this.ticketCount - 1) < 0 ){
            //throw new RuntimeException("오늘자 티켓팅이 마감되었습니다 (재고 부족)"); - 테스트 위해 주석
            log.error("진행중인 사람 : {}, 메세지 : 티켓팅이 마감되었습니다(재고 부족 - {} 개) ", thread, this.ticketCount);
            return;
        }
        this.ticketCount = this.ticketCount - 1;
        log.error("진행중인 사람 : {}, 메세지 : 티켓팅을 성공하셨습니다(남은 티켓 갯수 - {} 개) ", thread, this.ticketCount);
    }

    public boolean checkTicketingDate(LocalDate localDate) {
        return this.ticketingDate.equals(localDate);
    }

    public Integer getMusicalId() {
        return this.musical.getId();
    }
}
