difference()
{
    difference()
    {
        difference()
        {
            difference()
            {
                difference()
                {
                    difference()
                    {
                        difference()
                        {
                            difference()
                            {
                                difference()
                                {
                                    difference()
                                    {
                                        difference()
                                        {
                                            difference()
                                            {
                                                linear_extrude(height = 10.0, twist = 0.0, scale = 1.0, slices = 1, center = true)
                                                {
                                                    scale([120.0, 120.0])
                                                    {
                                                        M8();
                                                    }
                                                }
                                                translate([-37.5, 40.0, 5.0])
                                                {
                                                    linear_extrude(height = 10.0, twist = 0.0, scale = 1.0, slices = 1, center = true)
                                                    {
                                                        scale([30.0, 30.0])
                                                        {
                                                            M8();
                                                        }
                                                    }
                                                }
                                            }
                                            translate([-37.5, 0.0, 5.0])
                                            {
                                                linear_extrude(height = 10.0, twist = 0.0, scale = 1.0, slices = 1, center = true)
                                                {
                                                    scale([30.0, 30.0])
                                                    {
                                                        M8();
                                                    }
                                                }
                                            }
                                        }
                                        translate([-37.5, -40.0, 5.0])
                                        {
                                            linear_extrude(height = 10.0, twist = 0.0, scale = 1.0, slices = 1, center = true)
                                            {
                                                scale([30.0, 30.0])
                                                {
                                                    M8();
                                                }
                                            }
                                        }
                                    }
                                    translate([-37.5, 40.0, 5.0])
                                    {
                                        linear_extrude(height = 10.0, twist = 0.0, scale = 1.0, slices = 1, center = true)
                                        {
                                            scale([30.0, 30.0])
                                            {
                                                M8();
                                            }
                                        }
                                    }
                                }
                                translate([0.0, 40.0, 5.0])
                                {
                                    linear_extrude(height = 10.0, twist = 0.0, scale = 1.0, slices = 1, center = true)
                                    {
                                        scale([30.0, 30.0])
                                        {
                                            M8();
                                        }
                                    }
                                }
                            }
                            translate([0.0, 0.0, 5.0])
                            {
                                linear_extrude(height = 10.0, twist = 0.0, scale = 1.0, slices = 1, center = true)
                                {
                                    scale([30.0, 30.0])
                                    {
                                        M8();
                                    }
                                }
                            }
                        }
                        translate([0.0, -40.0, 5.0])
                        {
                            linear_extrude(height = 10.0, twist = 0.0, scale = 1.0, slices = 1, center = true)
                            {
                                scale([30.0, 30.0])
                                {
                                    M8();
                                }
                            }
                        }
                    }
                    translate([0.0, 40.0, 5.0])
                    {
                        linear_extrude(height = 10.0, twist = 0.0, scale = 1.0, slices = 1, center = true)
                        {
                            scale([30.0, 30.0])
                            {
                                M8();
                            }
                        }
                    }
                }
                translate([37.5, 40.0, 5.0])
                {
                    linear_extrude(height = 10.0, twist = 0.0, scale = 1.0, slices = 1, center = true)
                    {
                        scale([30.0, 30.0])
                        {
                            M8();
                        }
                    }
                }
            }
            translate([37.5, 0.0, 5.0])
            {
                linear_extrude(height = 10.0, twist = 0.0, scale = 1.0, slices = 1, center = true)
                {
                    scale([30.0, 30.0])
                    {
                        M8();
                    }
                }
            }
        }
        translate([37.5, -40.0, 5.0])
        {
            linear_extrude(height = 10.0, twist = 0.0, scale = 1.0, slices = 1, center = true)
            {
                scale([30.0, 30.0])
                {
                    M8();
                }
            }
        }
    }
    translate([37.5, 40.0, 5.0])
    {
        linear_extrude(height = 10.0, twist = 0.0, scale = 1.0, slices = 1, center = true)
        {
            scale([30.0, 30.0])
            {
                M8();
            }
        }
    }
}

module M8()
{
    polygon
    (
        points =
        [
            [-0.5, -0.5], 
            [0.5, -0.5], 
            [0.5, 0.5], 
            [-0.5, 0.5]
        ],
        paths =
        [
            [0, 1, 2, 3]
        ]
    );
}
