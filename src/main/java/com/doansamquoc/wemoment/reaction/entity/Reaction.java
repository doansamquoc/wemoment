package com.doansamquoc.wemoment.reaction.entity;

import com.doansamquoc.wemoment.common.enums.ReactionType;
import com.doansamquoc.wemoment.moment.entity.Moment;
import com.doansamquoc.wemoment.user.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Table(name = "reactions", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "moment_id", "reactor_id" })
})
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Reaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @ManyToOne
    @JoinColumn(name = "moment_id", nullable = false)
    Moment moment;

    @ManyToOne
    @JoinColumn(name = "reactor_id", nullable = false)
    User user;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    ReactionType reaction;
}
