package com.projectlarp.app.game;

import com.projectlarp.app.common.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "APP_GAMES")
public class Game extends AbstractEntity {
    public String name;
    public String style;
}
