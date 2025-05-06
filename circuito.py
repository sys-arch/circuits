from qiskit import QuantumRegister, ClassicalRegister, QuantumCircuit
from qiskit_aer import AerSimulator
import json

# Crear registros y circuito
qreg = QuantumRegister(6, 'q')
creg = ClassicalRegister(3, 'c')
circuit = QuantumCircuit(qreg, creg)

# Inicialización (si es necesaria)


# Lógica del circuito generada automáticamente
circuit.mcx([qreg[0], qreg[1], qreg[2]], qreg[3])

circuit.mcx([qreg[0], qreg[1], qreg[2]], qreg[4])

circuit.mcx([qreg[0], qreg[1], qreg[2]], qreg[5])



# Medición automática de los qubits de salida
circuit.measure(qreg[3], creg[0])
circuit.measure(qreg[4], creg[1])
circuit.measure(qreg[5], creg[2])


# Simulador y ejecución
simulator = AerSimulator()
job = simulator.run(circuit, shots=1000)
result = job.result()

# Obtener resultados
counts = result.get_counts(circuit)
print(counts)

# Guardar resultados en JSON
with open("resultados.json", "w") as f:
    json.dump(counts, f, indent=4)
