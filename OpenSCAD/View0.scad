module Plank() {
    linear_extrude(height = 100.0, twist = 0.0, scale = [1.0, 0.5, 0.5], slices = 1, center = true)
    {
        scale([10.0, 10.0])
        {
            polygon(points =
            [
                [-0.5, -0.5],
                [0.5, -0.5],
                [0.5, 0.5],
                [-0.5, 0.5]
            ],
            paths =
            [
                [0, 1, 2, 3]
            ]);
        }
    }
}

module PlankRow() {
//alter the number of planks in the row by changing the range
    for (i = [0:9]) {
        translate([i * (20 + gap), 0, 0]) {
            Plank();
        }
    }
}

// Set the desired gap between planks
gap = 2;

PlankRow();
