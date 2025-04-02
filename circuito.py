from qiskit import QuantumRegister, ClassicalRegister, QuantumCircuit, execute
from qiskit import Aer

qreg = QuantumRegister(6, 'q')
creg = ClassicalRegister(3, 'c')

circuit = QuantumCircuit(qreg, creg)
ONE = [0, 1]
ZERO = [1, 0]



circuit.x(q[0])
circuit.x(q[1])
circuit.mcx([q[0], q[1], q[2], ], q[5])
circuit.x(q[0])
circuit.x(q[1])

circuit.x(q[0])
circuit.x(q[2])
circuit.mcx([q[0], q[1], q[2], ], q[4])
circuit.x(q[0])
circuit.x(q[2])

circuit.mcx([q[0], q[1], q[2], ], q[3])





backend = Aer.get_backend('statevector_simulator')
job = backend.run(circuit, shots=100)
result = job.result()
histogram = result.get_counts()
print(histogram)