export let options = {
    stages: [
        { duration: '2m', target: 25 },   // Ramp up to 25 concurrent connections
        { duration: '10m', target: 25 },  // Hold for 10 minutes at 25 connections
        { duration: '2m', target: 0 },    // Ramp down to 0
        { duration: '2m', target: 100 },  // Ramp up to 100 concurrent connections
        { duration: '10m', target: 100 }, // Hold for 10 minutes at 100 connections
        { duration: '2m', target: 0 },    // Ramp down to 0
        { duration: '2m', target: 300 },  // Ramp up to 300 concurrent connections
        { duration: '10m', target: 300 }, // Hold for 10 minutes at 300 connections
        { duration: '2m', target: 0 },    // Ramp down to 0
    ],
    thresholds: {
        http_reqs: [`count>=5000000`], // Ensure the test runs for at least 5 million requests
        http_req_duration: ['p(95)<200'], // 95% of requests should complete in less than 200ms
        'http_req_failed{expected_response:true}': ['rate<0.01'], // Less than 1% failures
    }
};