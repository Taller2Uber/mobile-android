#!/bin/sh

echo "hola como estas"

function post_notification() {
  curl -X POST \
    https://fcm.googleapis.com/fcm/send \
    -H 'authorization: key=AAAAc3lcLr8:APA91bEjf0y6NSLjfjvPmbDT0kyadEtyu3KK7TLZ9QHG97LpIr9mhdmuE1DHlzkF_8MzPjNJSwNCilfYBkUgoBkQJUBYssqzJMeI0KYBzR0UbgHbAdJxZWEH-dCGxRodFzQtEwjtdV5-' \
    -H 'cache-control: no-cache' \
    -H 'content-type: application/json' \
    -d '{
      "to": "cF5EebuLvXg:APA91bFuGIrUaJ7g-11x4oXzX7Ycxh1NByrYdgIoPxdmZwtLf_aVppFnCdwOujWhSFXQeNlDx6WYm9ZBDySDMjtyM1JRMt7QBOxYIs4ySKeovaTHgE5b3qPxPJtLgAgmeG6VCnpGlfEC",
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
