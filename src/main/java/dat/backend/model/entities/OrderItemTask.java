package dat.backend.model.entities;

public enum OrderItemTask {
    STERN_UPPER_ENDS("understernbrædder til for & bag ende"),
    STERN_UPPER_SIDES("understernbrædder til siderne"),
    STERN_LOWER_ENDS("oversternbrædder til for & bag ende"),
    STERN_LOWER_SIDES("oversternbrædder til siderne"),
    RIM("Remme i sider, sadles ned i stolper"),
    RIM_SHED("Remme i sider, sadles ned il stolper (skur del, deles)"),
    SHED_Z("til z på bagside af dør"),
    SPAR("Spær, monteres på rem"),
    POLE("Stolper nedgraves 90 cm. i jord"),
    WATERBOARD_SIDES("vandbrædt på stern i sider"),
    WATERBOARD_ENDS("vandbrædt på stern i forende"),
    ROOF_TILE("tagplader monteres på spær"),
    ROOF_TILE_SCREWS("Skruer til tagplader"),
    FITTING_SPAR_RIM("Til montering af spær på rem"),
    CROSSWIND("Til vindkryds på spær"),
    SCREW_STERN_WATERBOARD("Til montering af stern & vandbrædt"),
    SCREW_UNIVERSAL_CROWSSWIND("Til montering af universalbeslag + hulbånd"),
    SCREW_RIM_POLE("Til montering af rem på stolper"),
    SCREW_OUTER_CLOTHING("til montering af yderste beklædning"),
    SCREW_INNER_CLOTHING("til montering af inderste beklædning"),
    SHED_CLOTHING("til beklædning af skur 1 på 2"),
    SHED_DOOR_LOCK("Til lås på dør til skur"),
    SHED_T_HINGE("Til skurdør"),
    SHED_SUPPORT_POSTS_SIDES("løsholter til skur sider"),
    SHED_SUPPORT_POSTS_GABLE("løsholter til skur gavle"),
    SHED_ANGLE_BRACKET("Til montering af løsholter til skur");




    private final String task;

    OrderItemTask(String task) {
        this.task = task;
    }

    public String getTask() {
        return this.task;
    }

    public static OrderItemTask getByValue(String value) {
        for (OrderItemTask task : OrderItemTask.values()) {
            if (task.getTask().equalsIgnoreCase(value)) {
                return task;
            }
        }
        throw new IllegalArgumentException("No enum constant found for value: " + value);
    }
}
