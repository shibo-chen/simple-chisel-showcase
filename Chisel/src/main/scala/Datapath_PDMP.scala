/* Version 1.2 
Negator: PipelinedDecoupledNegator
Negator Bandwidth: 64
Memory Bandwidth: 64
Interface: DECOUPLED
Encryption: NO
*/
package negator

import chisel3._
import chisel3.util._ 
import freechips.rocketchip.config.Parameters

class DatapathInterface extends Bundle{
    val input_data = Flipped(Valid(UInt(64.W)))
    val output_data = Valid(UInt(64.W)
}

class Datapath_PDMP extends Module{
    val io = IO(new DatapathInterface)
    
    val output_ready = Wire(Bool())
	val pipelinedDecoupledNegator = Module(new PipelinedDecoupledNegator(1, 64) )

	io.input_data <> pipelinedDecoupledNegator.io.input_data
    io.output_data <> pipelinedDecoupledNegator.io.output_data
    pipelinedDecoupledNegator.io.output_data.ready := true.B
    output_ready := pipelinedDecoupledNegator.io.input_data.ready
}