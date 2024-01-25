package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "rumble test", group = "Rumble")
public class RumbleTest extends LinearOpMode {
    public void runOpMode(){
        waitForStart();
        while (opModeIsActive()) {
            double px = gamepad1.left_stick_x;

            double rumble1 = (1-px)/2;
            double rumble2 = (1+px)/2;

            telemetry.addData("rumble1: ", rumble1);
            telemetry.addData("rumble2: ", rumble2);
            telemetry.update();

            gamepad1.rumble(rumble1, rumble2, 50);
        }
    }
}
