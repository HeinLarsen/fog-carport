linear_extrude(height = 210.0, twist = 0.0, scale = 1.0, slices = 1, center = false)
{
    scale([97.0, 97.0])
    {
        M8();
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
