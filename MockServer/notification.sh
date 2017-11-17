#!/bin/sh

echo "hola como estas"

function post_notification() {
  curl -X POST \
    https://fcm.googleapis.com/fcm/send \
    -H 'authorization: key=AAAAc3lcLr8:APA91bEjf0y6NSLjfjvPmbDT0kyadEtyu3KK7TLZ9QHG97LpIr9mhdmuE1DHlzkF_8MzPjNJSwNCilfYBkUgoBkQJUBYssqzJMeI0KYBzR0UbgHbAdJxZWEH-dCGxRodFzQtEwjtdV5-' \
    -H 'cache-control: no-cache' \
    -H 'content-type: application/json' \
    -d '{
      "to": "df7Bfmbs_KU:APA91bGIHoBUaFEVJ-HvGav__9D0rODHFwaC-aAfho6Tkbd5Ul83PzcOhc2yQ15-Hmtq12D9bqPBrD-WnxAZ8hxahQaz3UTYIz4DdpBNfjm61z7rrDmA6UKMNNbN3EI2O_jSZO8lZQ1q",
      "notification" : {
      	"body":"'$1'"
      }
  }'
}

BODY='{\"key\":\"value\",\"key2\":\"value2\"}'


# Definmo 3 tipos de notification

# Cuando selecciono a un camino, se le debe alertar a los conductores que
# Hay un camino a elegir

BODY='{\"rout\":\"<rout>\",\"passenger\":\"<passenger>\"}'


# Cuando el chofer seleccionó el camino se le debe alertar al pasajero que
# este lo acepto.

BODY='{\"driver\":\"<driver>\"}'

# Cuando el chofer llegó, le avisa al pasajero que llego, esto elimina el chat
# y tambien empieza a trackear las posiciones de ambos

# TODO: hacer este modulo

# Cuando termina el viaje, el chofer termina el pago y dejan de trackear

# TODO: hacer este modulo


# Cuando

post_notification $BODY
