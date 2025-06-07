import json
import random
import uuid
from datetime import datetime, timezone, timedelta

def generate_subscriptions(num_subscriptions, num_users):
    subscriptions = []

    # 1. Predefined User IDs (as UUIDs, similar to what you'd have in a real system)
    predefined_user_ids = [str(uuid.uuid4()) for _ in range(num_users)]
    print(f"Generated {num_users} unique user IDs.")

    # 2. Configuration for random data generation
    currency_pairs = [
        "GBP/USD", "EUR/JPY", "USD/CHF", "AUD/CAD", "NZD/USD",
        "USD/CAD", "EUR/USD", "GBP/JPY", "AUD/USD", "CAD/JPY"
    ]
    directions = ["ABOVE", "BELOW"]
    all_channels = ["PUSH", "EMAIL", "SMS"]
    statuses = ["ACTIVE", "INACTIVE"] # Most will be active for matching simulation

    # Base thresholds for realism (approximate mid-points)
    base_thresholds = {
        "GBP/USD": (1.2000, 1.3500, 4), # min, max, decimal_places
        "EUR/JPY": (150.000, 180.000, 3),
        "USD/CHF": (0.8800, 0.9500, 4),
        "AUD/CAD": (0.8800, 0.9400, 4),
        "NZD/USD": (0.5800, 0.6500, 4),
        "USD/CAD": (1.3000, 1.4000, 4),
        "EUR/USD": (1.0500, 1.1500, 4),
        "GBP/JPY": (190.000, 210.000, 3),
        "AUD/USD": (0.6400, 0.7000, 4),
        "CAD/JPY": (108.000, 128.000, 3)
    }

    # Generate subscriptions
    print(f"Generating {num_subscriptions} subscriptions...")
    for i in range(num_subscriptions):
        sub_id = str(uuid.uuid4())
        user_id = random.choice(predefined_user_ids)
        currency_pair = random.choice(currency_pairs)
        direction = random.choice(directions)

        min_thresh, max_thresh, decimals = base_thresholds.get(currency_pair, (1.000, 2.000, 4))
        threshold_value = round(random.uniform(min_thresh, max_thresh), decimals)

        # Randomly select 1 to 3 notification channels
        num_channels = random.randint(1, len(all_channels))
        notification_channels = random.sample(all_channels, num_channels)

        status = "ACTIVE" if random.random() < 0.90 else random.choice(["INACTIVE"]) # 90% active

        # Simulate creation over a recent period
        created_at = datetime.now(timezone.utc) - timedelta(days=random.randint(0, 90), hours=random.randint(0,23), minutes=random.randint(0,59))
        updated_at = created_at + timedelta(days=random.randint(0, 5)) # Update slightly after creation

        subscription = {
            "id": sub_id,
            "userId": user_id,
            "currencyPair": currency_pair,
            "threshold": threshold_value,
            "direction": direction,
            "notificationChannels": sorted(notification_channels), # Consistent order
            "status": status,
            "createdAt": created_at.isoformat(timespec='milliseconds'),
            "updatedAt": updated_at.isoformat(timespec='milliseconds'),
            "lastTriggeredAt": None # Initially null, updated by matching service
        }
        subscriptions.append(subscription)

    print(f"Successfully generated {len(subscriptions)} subscription records.")
    return subscriptions

# --- Script Execution ---
NUM_SUBSCRIPTIONS = 1000
NUM_UNIQUE_USERS = 50 # Subscriptions will be distributed among these users

generated_data = generate_subscriptions(NUM_SUBSCRIPTIONS, NUM_UNIQUE_USERS)

output_file_name = "subscriptions_and_users.json"
with open(output_file_name, 'w') as f:
    json.dump(generated_data, f, indent=2)

print(f"\nSaved generated subscriptions to {output_file_name}")