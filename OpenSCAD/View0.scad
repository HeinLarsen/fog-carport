union()
{
    union()
    {
        linear_extrude(height = 40.0, twist = 0.0, scale = 1.0, slices = 1, center = true)
        {
            scale([40.0, 10.0])
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
