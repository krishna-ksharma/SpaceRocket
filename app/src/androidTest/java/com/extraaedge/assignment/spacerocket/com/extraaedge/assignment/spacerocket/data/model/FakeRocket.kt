package com.extraaedge.assignment.spacerocket.com.extraaedge.assignment.spacerocket.data.model

import com.extraaedge.assignment.spacerocket.data.model.*

class FakeRocket {
    companion object {
        fun fakeData(): Rocket {
            return Rocket(
                id = "5e9d0d95eda69955f709d1eb",
                active = false,
                cost_per_launch = 6700000,
                success_rate_pct = 40,
                description = "The Falcon 1 was an expendable launch system privately developed and manufactured by SpaceX during 2006-2009. On 28 September 2008, Falcon 1 became the first privately-developed liquid-fuel launch vehicle to go into orbit around the Earth.",
                wikipedia = "https://en.wikipedia.org/wiki/Falcon_1",
                diameter = Height(12.0, 20.0),
                height = Height(12.0, 20.0),
                company = "SpaceX",
                country = "Republic of the Marshall Islands",
                first_flight = "2006-03-24",
                boosters = 0,
                stages = 2,
                name = "Falcon 1",
                flickr_images = emptyList(),
                payload_weights = emptyList(),
                landing_legs = LandingLegs(material = "", number = 0),
                mass = Mass(0, 0),
                engines = Engines(
                    engine_loss_max = 1,
                    isp = Isp(2, 0),
                    layout = "",
                    number = 9,
                    propellant_1 = "",
                    propellant_2 = "",
                    thrust_sea_level = Thrust(9, 8),
                    thrust_to_weight = 9.8,
                    thrust_vacuum = Thrust(2, 3),
                    type = "",
                    version = "0o"
                ),
                second_stage = FirstStage(
                    burn_time_sec = 1,
                    engines = 9,
                    fuel_amount_tons = 0.0,
                    reusable = false,
                    thrust_vacuum = Thrust(9, 8),
                    thrust_sea_level = Thrust(9, 8)
                ),
                first_stage = FirstStage(
                    burn_time_sec = 1,
                    engines = 9,
                    fuel_amount_tons = 0.0,
                    reusable = false,
                    thrust_vacuum = Thrust(9, 8),
                    thrust_sea_level = Thrust(9, 8)
                ),
                type = "rocket"
            )
        }
    }
}