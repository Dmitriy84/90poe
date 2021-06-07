package com.poe.kafka.respapi.springbootapp.dto;

import lombok.*;

import java.io.Serializable;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "messages")
public class KafkaMessageModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long key;
    private Long id;
    private String message;
}
